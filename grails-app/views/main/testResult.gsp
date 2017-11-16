<g:render template="/commons/header"/>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Resultado</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <g:each in="${options}" var="teams" status="index">
        <div class="col-lg-4">
            <div class="panel panel-${["green", "yellow", "red"][index]}">
                <div class="panel-heading">
                    Opci&oacute;n ${index + 1}
                </div>

                <div class="panel-body">
                    <g:each in="${teams}" var="team" status="index2">
                        <h2>Equipo: ${index2 + 1}</h2>
                        <g:each in="${team.peopleDtoList}" var="people">
                            <div>
                                ${people.name} - ${people.personality}
                            </div>
                        </g:each>
                        <div class="well">
                            <b>Eficiencia: ${(((teams.peopleDtoList*.size().sum() * 5) / 10) * team.weight)} %</b>
                        </div>
                    </g:each>
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>
    </g:each>
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <a href="/" class="btn btn-primary btn-lg btn-block">Volver</a>
        <br />
    </div>
</div>
<!-- /.row -->

<g:render template="/commons/footer"/>
