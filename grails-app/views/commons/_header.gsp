<!DOCTYPE html>
<html lang="es">

<g:render template="/commons/items/head"/>

<body>

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Equipo Óptimos</a>
        </div>
        <!-- /.navbar-header -->

        <g:render template="/commons/items/user_logued"/>

        <g:render template="/commons/items/menu"/>
    </nav>
    <div id="page-wrapper" style="min-height: 800px;">
