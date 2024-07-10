const urlProductos = "api/productos";
const urlCategorias = "api/categorias";

function saveProducto(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardar").data("id");    
    let producto = {
        id: id,
        nombre: $("#nombre").val(),
        descripcion: $("#descripcion").val(),
        precio: parseFloat($("#precio").val()),
        cantidad: parseInt($("#cantidad").val()),
        categoria: {
            id: $("#categoria").val()
        }
    };
    let metodo = (bandera == 1) ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: urlProductos,
        data: JSON.stringify(producto),
        dataType: "json",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if (data == 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'El producto ya está registrado',
                    showConfirmButton: false,
                    timer: 1500
                });				
            } else {
                let texto = bandera == 1 ? "guardado" : "actualizado";
                getTablaProductos();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha '+texto+' el producto',
                    showConfirmButton: false,
                    timer: 1500
                });
                clearProducto();
            }
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function deleteFilaProducto(id) {
    $.ajax({
        type: "DELETE",
        url: urlProductos + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado el producto',
                showConfirmButton: false,
                timer: 1500
            });
            getTablaProductos();
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function getTablaProductos() {
    $.ajax({
        type: "GET",
        url: urlProductos,
        dataType: "json",
        cache: false,
        success: function (data) {
            let t = $("#tablaProductos").DataTable();
            t.clear().draw(false);
            $.each(data, function (index, producto) {
                let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                               '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';
                t.row.add([producto.id, producto.nombre, producto.descripcion, producto.precio, producto.cantidad, producto.categoria.nombre, botonera]);
            });
            t.draw(false);
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function getFilaProducto(id) {
    $.ajax({
        type: "GET",
        url: urlProductos + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Producto");
            $("#nombre").val(data.nombre);
            $("#descripcion").val(data.descripcion);
            $("#precio").val(data.precio);
            $("#cantidad").val(data.cantidad);
            $("#categoria").val(data.categoria.id);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function clearProducto() {
    $("#modal-title").text("Nuevo Producto");
    $("#nombre").val("");
    $("#descripcion").val("");
    $("#precio").val("");
    $("#cantidad").val("");
    $("#categoria").val(""); // Asegúrate de resetear el select de categoría si es necesario
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {
    // Configuración de DataTable para la tabla de productos
    $("#tablaProductos").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ productos",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "10%" },
            { targets: 1, width: "20%" },
            { targets: 2, width: "20%" },
            { targets: 3, width: "10%" },
            { targets: 4, width: "10%" },
            { targets: 5, width: "20%" },
            { targets: 6, orderable: false, width: "10%" }
        ],
    });

    // Función para cargar las opciones del select de categorías
    function cargarCategorias() {
        $.ajax({
            type: "GET",
            url: urlCategorias,
            dataType: "json",
            cache: false,
            success: function (data) {
                $("#categoria").empty();
                $("#categoria").append('<option value="">Seleccione una Categoría</option>');
                $.each(data, function (index, categoria) {
                    $("#categoria").append('<option value="' + categoria.id + '">' + categoria.nombre + '</option>');
                });
            },
        }).fail(function () {
            // Manejo de errores
        });
    }

    // Llamada inicial para cargar las categorías
    cargarCategorias();

    clearProducto();

    $("#nuevo").click(function () {
        clearProducto();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        saveProducto(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Producto',
            text: "¿Está seguro de querer eliminar este producto?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFilaProducto(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        getFilaProducto(id);
    });

    getTablaProductos();
});
