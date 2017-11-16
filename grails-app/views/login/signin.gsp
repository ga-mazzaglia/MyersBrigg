<!DOCTYPE html>
<html lang="en">

<g:render template="/commons/items/head"/>

<g:render template="/commons/items/modals"/>

<script src="/js/deploy/controllers/signinController.js"></script>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div align="center">
                <h1>Myer-Brigg</h1>
            </div>
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Ingresar</h3>
                </div>

                <div class="panel-body">
                    <g:if test="${message?.text}">
                        <div class="alert alert-danger">
                            ${message.text}
                        </div>
                    </g:if>
                    <form id="signin" action="/login" role="form" method="POST">
                        <input type="hidden" value="${params.back ?: back}" placeholder="back" name="back" id="back" type="text" />

                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" value="" placeholder="username" name="username" id="username" type="text" autofocus>
                            </div>

                            <div class="form-group">
                                <input class="form-control" value="" placeholder="password" name="password" id="password" type="password"
                                       value="">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a onclick="jQuery('form').submit()" class="btn btn-lg btn-success btn-block">Entrar</a>
                        </fieldset>
                    </form>
                    <div style="text-align: center;padding: 5px;color: grey">user: test - pass: test</div>
                </div>
            </div>
        </div>
    </div>
</div>

<g:render template="/commons/items/foot"/>

<script type="text/javascript">
    $(function() {
        $('input').keydown(function(e){
            if(e.keyCode == 13){
                $("form").submit();
            }
        });
    });
    signinController.load();
</script>

</body>

</html>
