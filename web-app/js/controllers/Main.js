var Main = {

    _PEOPLES: [],

    init: function () {
        console.log("Main.init");

        Main.initBtns();

        Main._refreshPeoples();
    },

    initBtns: function () {
        jQuery("#addPeople").click(function () {
            Main._addPeople();
        });
        jQuery("#btnAnalize").click(function () {
            Main._analize();
        });
    },

    _addPeople: function () {
        var id = new Date().getTime();
        var name = jQuery("#peopleName").val();
        var personality = jQuery("#personality").val();
        Main._PEOPLES.push({id: id, name: name, personality: personality});
        Main._refreshPeoples();
        jQuery("#personality").val(jQuery("#personality option:first").val());
        jQuery("#peopleName").val("");
        jQuery("#peopleName").focus();
    },

    _removePeople: function (id) {
        console.log(id);
        for (index in Main._PEOPLES) {
            if (Main._PEOPLES[index].id == id) {
                Main._PEOPLES.splice(index, 1);
                console.log(Main._PEOPLES);
            }
        }
        Main._refreshPeoples()
    },

    _refreshPeoples: function () {
        jQuery("#peoples").html("");

        for (index in Main._PEOPLES) {
            var template = jQuery("#people_added_template").html();
            template = template.replace(/\{ID}/g, Main._PEOPLES[index].id);
            template = template.replace("[PEOPLE_NAME]", Main._PEOPLES[index].name);
            template = template.replace("[PERSONALITY]", Main._PEOPLES[index].personality);
            jQuery("#peoples").append(template);
        }
        if (Main._PEOPLES.length == 0) {
            jQuery("#peoples").append('<div style="text-align: center; color: grey;">debe agregar personas</div>');
            jQuery("#teamsContent").addClass("hidden");
        } else {
            Main.refreshComboGroupQuantity();
            Main.refreshComboOptimalGroupQuantity();
            jQuery("#teamsContent").removeClass("hidden");
        }
    },

    refreshComboGroupQuantity: function () {
        jQuery("#teams_quantity").html("")
        var quantity = Main._PEOPLES.length;
        for (var i = 2; i < (quantity / 2); i++) {
            jQuery("#teams_quantity").append("<option value='" + i + "'>" + i + "</option>")
        }
        console.log(quantity);
    },

    refreshComboOptimalGroupQuantity: function () {
        jQuery("#optimal_teams").html("")
        var quantity = Main._PEOPLES.length;
        for (var i = 1; i < (quantity / 2); i++) {
            jQuery("#optimal_teams").append("<option value='" + i + "'>" + i + "</option>")
        }
    },

    _analize: function () {
        var teams = jQuery("#teams_quantity").val();
        var optimal = jQuery("#optimal_teams").val();

        var peoples = [];
        for (people in Main._PEOPLES) {
            console.log(people, Main._PEOPLES[people]);
            peoples.push(Main._PEOPLES[people].name + "," + Main._PEOPLES[people].personality);
        }

        if (peoples.length == 0) {
            jQuery("#peopleName").focus();
            return
        }
        jQuery("#form_peoples").val(peoples.join("|"));
        jQuery("#form_teams").val(teams);
        jQuery("#form_optimal_teams").val(optimal);
        jQuery("#btnAnalize")
            .removeClass("btn-success")
            .addClass("btn-default")
            .html("analizando...")
            .focus()
            .attr("disabled", true)
        jQuery("form").submit();
    },

    testAddPeoples: function () {
        Main._PEOPLES = [
            {id: 1, name: "Test1", personality: "ENTJ"},
            {id: 2, name: "Test2", personality: "ESFJ"},
            {id: 3, name: "Test3", personality: "INFP"},
            {id: 4, name: "Test4", personality: "INTP"},
            {id: 5, name: "Test5", personality: "ISFJ"},
            {id: 6, name: "Test6", personality: "ISTP"},
            {id: 7, name: "Test7", personality: "ESTP"},
            {id: 8, name: "Test8", personality: "ENTP"},
            {id: 9, name: "Test9", personality: "INTJ"},
        ];
        Main.init();
    },
}
jQuery(document).ready(Main.init);
