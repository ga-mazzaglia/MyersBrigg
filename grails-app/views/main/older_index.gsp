<g:render template="/commons/header"/>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Realizar Test</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="header_take_test">
            <!--
            <img src="/images/testHeader.png"/>
            -->
            <div class="row">
                <div class="col-lg-6 ">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <i class="fa fa-clock-o fa-5x"></i>
                            <h4>Tiempo</h4>

                            <p>Solo te tomará 15 minutos.</p>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
                <div class="col-lg-6">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <i class="fa fa-smile-o fa-5x"></i>
                            <h4>Presición</h4>

                            <p>Intenta ser lo mas honesto posible.</p>
                        </div>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </div>
        </div>

        <div class="list-group">
            <form id="form_analize" method="POST" action="/analize" role="form">
                <g:each in="${items}" var="item" status="i">
                    <div class="list-group-item">
                        <div class="item_question">
                            <div class="index">
                                #${i + 1}
                            </div>

                            <div class="content">
                                <h4>${item.question}</h4>
                                <button type="button"
                                        class="btn btn-default btn-lg"
                                        attr-id="${item.id}"
                                        attr-value="A">SI</button>
                                <button type="button"
                                        class="btn btn-default btn-lg"
                                        attr-id="${item.id}"
                                        attr-value="B">NO</button>
                                <input id="${item.id}" type="hidden" class="answer-question" name="answer" value=""/>
                            </div>
                        </div>
                    </div>
                </g:each>
            </form>
        </div>

        <div class="footer_take_test">
            <button id="analize" type="button" class="btn btn-success btn-lg btn-block">Analizar</button>
        </div>
    </div>
</div>
<!-- /.row -->

<g:render template="/commons/footer"/>
