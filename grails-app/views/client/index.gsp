<%--
  Created by IntelliJ IDEA.
  User: mparra
  Date: 2019-04-26
  Time: 09:29
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Consumidor Agencias</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        Filtrar Agencias
                        <button class="btn btn-primary btn-sm float-right" id="collapseFilters" onclick="flipCollapseState()">Filtrar</button>
                    </div>
                    <div id="panel-filtro" class="card-body">
                        <form id="filtros" action="/obtenerDatos" method="post">
                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="site_id">Site Id *</label>
                                    <input type="text" class="form-control" id="site_id" name="site_id" placeholder="Identificador de Pais"
                                           onkeyup="this.value = this.value.toUpperCase();" required>
                                    <small id="site_idHelp" class="form-text text-muted">Ej. MLA, MLB, MLM, MLC</small>
                                </div>
                                <div class="form-group col">
                                    <label for="payment_method_id">Método de Pago *</label>
                                    <input type="text" class="form-control" id="payment_method_id" name="payment_method_id" placeholder="Método de Pago" required>
                                    <small id="payment_method_idHelp" class="form-text text-muted">Ej. rapipago, pagofacil</small>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="lat">Latitud</label>
                                    <input type="number" class="form-control" id="lat" name="lat" placeholder="Latitud" step="0.000001">
                                    <small id="latHelp" class="form-text text-muted">Ej. -31.412971</small>
                                </div>
                                <div class="form-group col">
                                    <label for="long">Longitud</label>
                                    <input type="number" class="form-control" id="long" name="long" placeholder="Longitud" step="0.000001">
                                    <small id="longdHelp" class="form-text text-muted">Ej. -64.18758</small>
                                </div>
                                <div class="form-group col">
                                    <label for="radio">Radio de búsqueda</label>
                                    <input type="number" class="form-control" id="radio" name="radio" placeholder="Radio de búsqueda en metros">
                                    <small id="radioHelp" class="form-text text-muted">Ej. 600</small>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="limit">Limitar resultados</label>
                                    <input type="number" class="form-control" id="limit" name="limit" placeholder="Número de resultados">
                                </div>
                                <div class="form-group col">
                                    <label for="offset">Offset</label>
                                    <input type="number" class="form-control" id="offset" name="offset" placeholder="Offset">
                                    <small id="offsetHelp" class="form-text text-muted">A partir de que resultado se muestra</small>
                                </div>
                                <div class="form-group col">
                                    <label for="radio">Ordenar por: </label>
                                    <select class="form-control" id="order_by" name="order_by">
                                        <option value="AGENCY_CODE">Código de Agencia</option>
                                        <option value="ADDRESS_LINE">Dirección</option>
                                        <option value="DISTANCE">Distancia</option>
                                    </select>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary" id="llamarApi">Solicitar Agencias</button>
                        </form>
                    </div>
                </div>

                <div class="card mt-4">
                    <div class="card-header">
                        Agencias
                    </div>
                    <div class="card-body" id="resultados">

                    </div>
                </div>
            </div>
        </div>
    </div>

<script
        src="https://code.jquery.com/jquery-3.4.0.min.js"
        integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script>

    function flipCollapseState() {
        if (jQuery('#panel-filtro').hasClass("collapse")){
            jQuery('#panel-filtro').removeClass("collapse");
        } else {
            jQuery('#panel-filtro').addClass("collapse");
        };
    }

    $(document).on('submit', '#filtros', function(event){
        event.preventDefault();

        var form = $('#filtros');

        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function (data) {
                console.log('Submission was successful.');
                console.log(data);
                $('#resultados').html(data);
            },
            error: function (data) {
                console.log('An error occurred.');
                console.log(data);
                $('#resultados').html("Error al obtener resultados, consulte la consola.");
            },
        });

        flipCollapseState();
    });
</script>
</body>
</html>