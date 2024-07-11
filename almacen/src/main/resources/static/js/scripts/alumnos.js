const urlAlumnos = "api/alumnos";
const urlAulas = "api/aulas";

function saveAlumno(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardar").data("id");    
    let alumno = {
        id: id,
        nombre: $("#nombre").val(),
        descripcion: $("#descripcion").val(),
        peso: parseFloat($("#peso").val()),
        edad: parseInt($("#edad").val()),
        aula: {
            id: $("#aula").val()
        }
    };
    let metodo = (bandera == 1) ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: urlAlumnos,
        data: JSON.stringify(alumno),
        dataType: "json",
        contentType: "application/json",
        cache: false,
        success: function (data) {
            if (data == 0) {
                Swal.fire({
                    icon: 'error',
                    title: 'El alumno ya está registrado',
                    showConfirmButton: false,
                    timer: 1500
                });				
            } else {
                let texto = bandera == 1 ? "guardado" : "actualizado";
                getTablaAlumnos();
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha '+texto+' el alumno',
                    showConfirmButton: false,
                    timer: 1500
                });
                clearAlumno();
            }
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function deleteFilaAlumno(id) {
    $.ajax({
        type: "DELETE",
        url: urlAlumnos + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado el alumno',
                showConfirmButton: false,
                timer: 1500
            });
            getTablaAlumnos();
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function getTablaAlumnos() {
    $.ajax({
        type: "GET",
        url: urlAlumnos,
        dataType: "json",
        cache: false,
        success: function (data) {
            let t = $("#tablaAlumnos").DataTable();
            t.clear().draw(false);
            $.each(data, function (index, alumno) {
                let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                               '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';
                t.row.add([alumno.id, alumno.nombre, alumno.descripcion, alumno.peso, alumno.edad, alumno.aula.nombre, botonera]);
            });
            t.draw(false);
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function getFilaAlumno(id) {
    $.ajax({
        type: "GET",
        url: urlAlumnos + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Alumno");
            $("#nombre").val(data.nombre);
            $("#descripcion").val(data.descripcion);
            $("#peso").val(data.peso);
            $("#edad").val(data.edad);
            $("#aula").val(data.aula.id);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {
        // Manejo de errores
    });
}

function clearAlumno() {
    $("#modal-title").text("Nuevo Alumno");
    $("#nombre").val("");
    $("#descripcion").val("");
    $("#peso").val("");
    $("#edad").val("");
    $("#aula").val(""); // Asegúrate de resetear el select de categoría si es necesario
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}

$(document).ready(function () {
    // Configuración de DataTable para la tabla de alumnos
    $("#tablaAlumnos").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ alumnos",
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
    function cargarAulas() {
        $.ajax({
            type: "GET",
            url: urlAulas,
            dataType: "json",
            cache: false,
            success: function (data) {
                $("#aula").empty();
                $("#aula").append('<option value="">Seleccione una Aula</option>');
                $.each(data, function (index, aula) {
                    $("#aula").append('<option value="' + aula.id + '">' + aula.nombre + '</option>');
                });
            },
        }).fail(function () {
            // Manejo de errores
        });
    }

    // Llamada inicial para cargar las categorías
    cargarAulas();

    clearAlumno();

    $("#nuevo").click(function () {
        clearAlumno();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        saveAlumno(bandera);
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Alumno',
            text: "¿Está seguro de querer eliminar este alumno?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFilaAlumno(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        getFilaAlumno(id);
    });

    getTablaAlumnos();
});
