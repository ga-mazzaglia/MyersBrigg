</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<g:render template="/commons/items/foot"/>
<g:render template="/commons/items/modals"/>

<script>
    // tooltip
    $('[data-toggle=tooltip]').tooltip({
        container: "body"
    })
    // popover
    $("[data-toggle=popover]").popover();

    // datepicker
    $(".datepicker").datepicker({
        dateFormat: "yy-mm-dd"
    });

    setInterval(function () {
        jQuery(".alert").fadeOut();
    }, 2000);

    jQuery(document).ready(function () {
        $(".sidebar-nav a").removeClass("active");
        $(".sidebar-nav a#${params?.controller?.toLowerCase()}_${params?.action?.GET?.toLowerCase()}").addClass("active");
    });
</script>
</body>
</html>
