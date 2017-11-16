/**
 * Created by gmazzaglia on 21/7/16.
 */
var Utils = {
    showAlert: function (title, message, callback) {
        jQuery("#alertModal #myModalLabel").html(title);
        jQuery("#alertModal #myModalText").html(message);

        jQuery("#alertModal").on('shown.bs.modal', function () {
            $('#alertModal #btn_acept').focus();
        });
        if(callback == undefined){
            jQuery("#alertModal").modal("show").on('hide.bs.modal', function (e) {

            })
        } else {
            jQuery("#alertModal").modal("show").on('hide.bs.modal', callback)
        }
    },

    showConfirm: function (title, message, type, callback) {
        jQuery("#confirmModal #fa-icon").addClass("fa-" + type);
        jQuery("#confirmModal #myModalLabel").html(title);
        jQuery("#confirmModal #myModalText").html(message);
        jQuery("#confirmModal").on('shown.bs.modal', function () {
            $('#confirmModal #btn-modal-confirm-ok').focus();
        });
        jQuery("#confirmModal").modal("show");
        jQuery("#confirmModal #btn-modal-confirm-ok").click(function (e) {
            e.preventDefault()
            if (callback != undefined) {
                e.preventDefault()
                callback(e);
                jQuery("#confirmModal").modal("hide");
            }
        })
    }
}