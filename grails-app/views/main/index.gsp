<g:render template="/commons/header"/>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Inicio</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-user-plus"></i> Agregar personas
            </div>

            <div class="panel-body">
                <div class="form-group">
                    <label for="peopleName">Nombre de la persona</label>
                    <input class="form-control" type="text" id="peopleName" autofocus/>
                </div>

                <div class="form-group">
                    <label for="personality">Personalidad</label>
                    <select class="form-control" id="personality">
                        <g:each in="${personalities}" var="perso">
                            <option value="${perso}">${perso}</option>
                        </g:each>
                    </select>
                </div>

                <button id="addPeople" type="button" class="btn btn-primary btn-lg btn-block">Agregar persona</button>
            </div>
        </div>
    </div>
</div>
<!-- /.row -->

<div class="row" id="teamsContent">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-group"></i> Equipos
            </div>

            <div class="panel-body">
                <div class="form-group">
                    <label>Personas</label>
                    <input type="text" class="hidden" name="peoples"/>

                    <div id="peoples">
                        <div style="text-align: center; color: grey;">agregar personas</div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="teams_quantity">Cantidad de equipos</label>
                    <select class="form-control" id="teams_quantity" name="teams_quantity"></select>
                </div>

                <div class="form-group hidden">
                    <label for="optimal_teams">Equipos &oacute;ptimos</label>
                    <select class="form-control" id="optimal_teams" name="optimal_teams"></select>
                </div>

                <button id="btnAnalize" type="button" class="btn btn-success btn-lg btn-block">Analizar</button>
            </div>
        </div>
    </div>
</div>
<!-- /.row -->

<div id="people_added_template" class="hidden">
    <div class="btn btn-block btn-dropbox btn-default" style="text-align: left;" id="people_{ID}">
        <div style="display: inline">
            <i class="fa fa-user"></i> [PEOPLE_NAME] ([PERSONALITY])
        </div>

        <div style="float: right">
            <i id="{ID}" class="fa fa-trash" style="left: 0px;" onclick="Main._removePeople({ID})"></i>
        </div>
    </div>
</div>

<form action="/analize" method="GET" class="hidden">
    <input type="text" name="peoples" id="form_peoples"/>
    <input type="text" name="teams" id="form_teams"/>
    <input type="text" name="optimals" id="form_optimal_teams"/>
</form>
<g:render template="/commons/footer"/>
