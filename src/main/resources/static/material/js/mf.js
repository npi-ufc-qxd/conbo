$(document).ready(function() {
	$(this).ajaxSend(function(event, jqxhr, settings) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		jqxhr.setRequestHeader(header, token);
	});
	
    mf_base.doInit();
});

var _context = $("meta[name='_context']").attr("content");
if(_context == null){
	_context = "";
}

jQuery.fn.extend({
    exists: function () {
    	return $(this).length > 0;
    }
});

var mf_base = function() {
    
    var initMainContainer = function() {
        $(".main").height($("body").height() - $("header").height());
    }
    
    var initSideBar = function() {
        try {
            $(".button-collapse-hide").sideNav({
            	
            });
            $('.collapsible').collapsible();

            $(".button-collapse-minimalize").click(function() {
            	$("header, nav, main, .side-nav, .side-nav i, .side-nav i").removeClass("no-transition");
            	$(".side-nav, main, header, footer").toggleClass("smaller");
            	Cookies.set("mf-base-sidebar-smaller", $(".side-nav").hasClass("smaller"));
            });
            
            var smaller = Cookies.get("mf-base-sidebar-smaller") === "true";
            if(smaller) {
            	$(".side-nav, main, header, footer").toggleClass("smaller");
            }
        } catch(err) {
            // ...
        }
        
    }
    
    var initCharts = function() {
    	try {
	    	Chart.defaults.global.defaultFontColor = "#37474F";
	    	Chart.defaults.global.defaultFontFamily = "Roboto";
	    	Chart.defaults.global.defaultFontSize = 12;
    	} catch (err) {
    		// ...
    	}
    }

    var initMask = function() {
        try {
            $('.cep').mask('00000-000');
            $('.phone').mask('(00) 00000-0000', {reverse: false});
            $('.cpf').mask('000.000.000-00', {reverse: false});
            $('.money').mask('000.000.000.000.000,00', {reverse: true});
            
            $("form").submit(function(event) {
            	
            	$("input.money").each(function(_, el) {
            		$(el).val( $(el).val().replace(/[.,]/g, function(x) {
            		    return x == ',' ? '.' : '';
            		}) );
            	});
            	            	
            });
        } catch (err) {
            // ...
        }
    }
    
    var initSelfSampling = function() {
    	
    	var animate = function() {
    		setTimeout(function() {
    			$(".self-sampled").addClass("move-left");
    		}, 100);
    		setTimeout(function() {
    			$(".self-sampled").removeClass("move-left");
    			$(".self-sampled").addClass("move-right");
    		}, 200);
    		setTimeout(function() {
    			$(".self-sampled").removeClass("move-right");
    			$(".self-sampled").addClass("move-left");
    		}, 300);
    		setTimeout(function() {
    			$(".self-sampled").removeClass("move-left");
    		}, 400);
    	};
    	
    	setTimeout(function() {
    		animate();
    		setInterval(function() {
        		animate();
        	}, 5000);
    	}, 1800);
    	
    }
    
    var initAlerts = function() {
    	
    	var getIconByType = function(type) {
    		return (
    			(type == 'info')   ? 'info':
    			(type == 'warning')? 'warning':
    			(type == 'success')? 'check':
    			(type == 'error')  ? 'error': ''
    		);
    	}
    	var getColorByType = function(type) {
    		
    		return (
    			(type == 'info')    ? 'blue':
    			(type == 'warning') ? 'deep-orange':
    			(type == 'success') ? 'green':
    			(type == 'error')   ? 'red': 'black'
    		);
    	}
    	
    	var delayAmount = 0;
   		$(".alert-message").each(function(_, el) {
   			$(el).remove();
    			var text = $(el).text();
        		var type = $(el).data("type").toLowerCase();
        		var delay = $(el).data("delay");
        		var icon = getIconByType(type);
        		var color = getColorByType(type);
        		var content = $('<div class="valign-wrapper"><i class="material-icons ' + color + '-text">' + icon + '</i><span class="' + color + '-text">' + text + '</span></div>');

        	setTimeout(function() {    			
        		Materialize.toast(content, delay, 'alert-' + type + ' rounded');
    		}, delayAmount);
        	
        	delayAmount += 500;
        	
       	});
    }

    var hideForeground = function() {
    	$("body").removeClass("no-transition");
    	$(".mf-foreground").fadeOut("slow");
    }

    var initConfirm = function() {
        try {
            $(".confirm").confirm({
                "confirmButton": "Sim", 
                "cancelButton": "Não"
            });
        } catch(err) { }
    }

    var initDataTableInElement = function(el, setts) {
        var orderCols = (el.data("sort-col") == undefined)? [] : el.data("sort-col").toString().split(",");
        var orderDirs = (el.data("sort-direction") === undefined)? [] : el.data("sort-direction").toString().split(",");
        var order = [];

        orderCols.forEach(function(col, i) {
            if(!isNaN(parseInt(col))) {
                order.push([parseInt(col), orderDirs[i]]);
            }
        });

        var dataTable = el.DataTable(
            $.extend({ 
                "order": order, 
                "pagingType": "full_numbers", 
                "language": {
                    "sEmptyTable": "Nenhum registro encontrado",
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
                    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
                    "sInfoPostFix": "",
                    "sInfoThousands": ".",
                    "sLengthMenu": "_MENU_ resultados por página",
                    "sLoadingRecords": "Carregando...",
                    "sProcessing": "Processando...",
                    "sZeroRecords": "Nenhum registro encontrado",
                    "sSearch": "Filtrar",
                    "oPaginate": {
                        "sNext": "Próximo",
                        "sPrevious": "Anterior",
                        "sFirst": "Primeiro",
                        "sLast": "Último"
                    },
                    "oAria": {
                        "sSortAscending": ": Ordenar colunas de forma ascendente",
                        "sSortDescending": ": Ordenar colunas de forma descendente"
                    }
                }
            }, setts)
        );
        
        $('select').material_select();

        var searchInput = $(el.data("filter"));
        searchInput.keyup(function() {
            dataTable.search( $(this).val() ).draw();
        });
        
        return dataTable;
    }

    var initDataTables = function() {
        $(".datatable").each(function(_, el) {
            var table = $(el);
            initDataTableInElement(table);
        });
    }

    var initLimit = function() {
    	$(".limit").each(function(_, e) {
    		var el = $(e);
    		var text = el.text();

    		el.addClass("tooltipped");
    		el.attr("data-tooltip", text);
    		el.tooltip();

    		var maxLength = el.data("max-length");
    		if(text && maxLength && text.length > maxLength) {
    			text = text.substring(0, maxLength) + "...";
    		}
    		el.text(text);
    	});
    }

    return {
        
        doInit : function() {
            initMainContainer();
            initSideBar();
            initCharts();
            initMask();
            initSelfSampling();
            initAlerts();
            initConfirm();
            initDataTables();
            initLimit();
            
            hideForeground();            
        },
        
        doAddChart : function(el, type, labels, datasets) {
        	var ctx = el[0];
			return new Chart(ctx, {
			    type: type,
			    data: {
			        labels: labels,
			        datasets: datasets
			    },
			    options: { 
			        elements: {
				        line: {
				        }, 
				        point: {
					    	radius: 7
				        }
			        }
			        
			    }
			});
			
        }, 
        
        doAddDataTable : initDataTableInElement, 

        doAlertSet : function(alertSet) {
            for(k in alertSet) {
                var alert = alertSet[k];
                Materialize.toast(alert.message, alert.delay);
            }
        }, 
        
        doGetCurrentDate : function() {
        	var today = new Date();
        	var dd = today.getDate();
        	var mm = today.getMonth()+1; //January is 0!
        	var yyyy = today.getFullYear();

        	if(dd<10) {
        	    dd='0'+dd
        	} 

        	if(mm<10) {
        	    mm='0'+mm
        	} 

        	today = mm + '/' + dd + '/' + yyyy;
        	return today;
        }
        
    };
    
} ();

var mf_animator = function() {

    return {

        doSequentially : function(toDo) {
            toDo = toDo || [];
            var accTime = 0;
            for(k in toDo) {
                accTime += toDo[k].after;
                if(toDo[k])
                    setTimeout(toDo[k].do, accTime);
            }
        }

    };

} ();

var mf_color = function() {

    __md = {
        red_50: "#FFEBEE", 
        red_100: "#FFCDD2", 
        red_200: "#EF9A9A", 
        red_300: "#E57373", 
        red_400: "#EF5350", 
        red_500: "#F44336", 
        red_600: "#E53935", 
        red_700: "#D32F2F", 
        red_800: "#C62828", 
        red_900: "#B71C1C", 
        red_A100: "#FF8A80", 
        red_A200: "#FF5252", 
        red_A400: "#FF1744", 
        red_A700: "#D50000", 

        
        pink_50: "#FCE4EC", 
        pink_100: "#F8BBD0", 
        pink_200: "#F48FB1", 
        pink_300: "#F06292", 
        pink_400: "#EC407A", 
        pink_500: "#E91E63", 
        pink_600: "#D81B60", 
        pink_700: "#C2185B", 
        pink_800: "#AD1457", 
        pink_900: "#880E4F", 
        pink_A100: "#FF80AB", 
        pink_A200: "#FF4081", 
        pink_A400: "#F50057", 
        pink_A700: "#C51162", 

        
        purple_50: "#F3E5F5", 
        purple_100: "#E1BEE7", 
        purple_200: "#CE93D8", 
        purple_300: "#BA68C8", 
        purple_400: "#AB47BC", 
        purple_500: "#9C27B0", 
        purple_600: "#8E24AA", 
        purple_700: "#7B1FA2", 
        purple_800: "#6A1B9A", 
        purple_900: "#4A148C", 
        purple_A100: "#EA80FC", 
        purple_A200: "#E040FB", 
        purple_A400: "#D500F9", 
        purple_A700: "#AA00FF", 

        deep_purple_50: "#EDE7F6", 
        deep_purple_100: "#D1C4E9", 
        deep_purple_200: "#B39DDB", 
        deep_purple_300: "#9575CD", 
        deep_purple_400: "#7E57C2", 
        deep_purple_500: "#673AB7", 
        deep_purple_600: "#5E35B1", 
        deep_purple_700: "#512DA8", 
        deep_purple_800: "#4527A0", 
        deep_purple_900: "#311B92", 
        deep_purple_A100: "#B388FF", 
        deep_purple_A200: "#7C4DFF", 
        deep_purple_A400: "#651FFF", 
        deep_purple_A700: "#6200EA", 

        
        indigo_50: "#E8EAF6", 
        indigo_100: "#C5CAE9", 
        indigo_200: "#9FA8DA", 
        indigo_300: "#7986CB", 
        indigo_400: "#5C6BC0", 
        indigo_500: "#3F51B5", 
        indigo_600: "#3949AB", 
        indigo_700: "#303F9F", 
        indigo_800: "#283593", 
        indigo_900: "#1A237E", 
        indigo_A100: "#8C9EFF", 
        indigo_A200: "#536DFE", 
        indigo_A400: "#3D5AFE", 
        indigo_A700: "#304FFE", 

        blue_50: "#E3F2FD", 
        blue_100: "#BBDEFB", 
        blue_200: "#90CAF9", 
        blue_300: "#64B5F6", 
        blue_400: "#42A5F5", 
        blue_500: "#2196F3", 
        blue_600: "#1E88E5", 
        blue_700: "#1976D2", 
        blue_800: "#1565C0", 
        blue_900: "#0D47A1", 
        blue_A100: "#82B1FF", 
        blue_A200: "#448AFF", 
        blue_A400: "#2979FF", 
        blue_A700: "#2962FF", 

        light_blue_50: "#E1F5FE", 
        light_blue_100: "#B3E5FC", 
        light_blue_200: "#81D4fA", 
        light_blue_300: "#4fC3F7", 
        light_blue_400: "#29B6FC", 
        light_blue_500: "#03A9F4", 
        light_blue_600: "#039BE5", 
        light_blue_700: "#0288D1", 
        light_blue_800: "#0277BD", 
        light_blue_900: "#01579B", 
        light_blue_A100: "#80D8FF", 
        light_blue_A200: "#40C4FF", 
        light_blue_A400: "#00B0FF", 
        light_blue_A700: "#0091EA", 

        
        cyan_50: "#E0F7FA", 
        cyan_100: "#B2EBF2", 
        cyan_200: "#80DEEA", 
        cyan_300: "#4DD0E1", 
        cyan_400: "#26C6DA", 
        cyan_500: "#00BCD4", 
        cyan_600: "#00ACC1", 
        cyan_700: "#0097A7", 
        cyan_800: "#00838F", 
        cyan_900: "#006064", 
        cyan_A100: "#84FFFF", 
        cyan_A200: "#18FFFF", 
        cyan_A400: "#00E5FF", 
        cyan_A700: "#00B8D4", 

        
        teal_50: "#E0F2F1", 
        teal_100: "#B2DFDB", 
        teal_200: "#80CBC4", 
        teal_300: "#4DB6AC", 
        teal_400: "#26A69A", 
        teal_500: "#009688", 
        teal_600: "#00897B", 
        teal_700: "#00796B", 
        teal_800: "#00695C", 
        teal_900: "#004D40", 
        teal_A100: "#A7FFEB", 
        teal_A200: "#64FFDA", 
        teal_A400: "#1DE9B6", 
        teal_A700: "#00BFA5", 

        
        green_50: "#E8F5E9", 
        green_100: "#C8E6C9", 
        green_200: "#A5D6A7", 
        green_300: "#81C784", 
        green_400: "#66BB6A", 
        green_500: "#4CAF50", 
        green_600: "#43A047", 
        green_700: "#388E3C", 
        green_800: "#2E7D32", 
        green_900: "#1B5E20", 
        green_A100: "#B9F6CA", 
        green_A200: "#69F0AE", 
        green_A400: "#00E676", 
        green_A700: "#00C853", 

        light_green_50: "#F1F8E9", 
        light_green_100: "#DCEDC8", 
        light_green_200: "#C5E1A5", 
        light_green_300: "#AED581", 
        light_green_400: "#9CCC65", 
        light_green_500: "#8BC34A", 
        light_green_600: "#7CB342", 
        light_green_700: "#689F38", 
        light_green_800: "#558B2F", 
        light_green_900: "#33691E", 
        light_green_A100: "#CCFF90", 
        light_green_A200: "#B2FF59", 
        light_green_A400: "#76FF03", 
        light_green_A700: "#64DD17", 

        lime_50: "#F9FBE7", 
        lime_100: "#F0F4C3", 
        lime_200: "#E6EE9C", 
        lime_300: "#DCE775", 
        lime_400: "#D4E157", 
        lime_500: "#CDDC39", 
        lime_600: "#C0CA33", 
        lime_700: "#A4B42B", 
        lime_800: "#9E9D24", 
        lime_900: "#827717", 
        lime_A100: "#F4FF81", 
        lime_A200: "#EEFF41", 
        lime_A400: "#C6FF00", 
        lime_A700: "#AEEA00", 

        yellow_50: "#FFFDE7", 
        yellow_100: "#FFF9C4", 
        yellow_200: "#FFF590", 
        yellow_300: "#FFF176", 
        yellow_400: "#FFEE58", 
        yellow_500: "#FFEB3B", 
        yellow_600: "#FDD835", 
        yellow_700: "#FBC02D", 
        yellow_800: "#F9A825", 
        yellow_900: "#F57F17", 
        yellow_A100: "#FFFF82", 
        yellow_A200: "#FFFF00", 
        yellow_A400: "#FFEA00", 
        yellow_A700: "#FFD600", 

        amber_50: "#FFF8E1", 
        amber_100: "#FFECB3", 
        amber_200: "#FFE082", 
        amber_300: "#FFD54F", 
        amber_400: "#FFCA28", 
        amber_500: "#FFC107", 
        amber_600: "#FFB300", 
        amber_700: "#FFA000", 
        amber_800: "#FF8F00", 
        amber_900: "#FF6F00", 
        amber_A100: "#FFE57F", 
        amber_A200: "#FFD740", 
        amber_A400: "#FFC400", 
        amber_A700: "#FFAB00", 

        orange_50: "#FFF3E0", 
        orange_100: "#FFE0B2", 
        orange_200: "#FFCC80", 
        orange_300: "#FFB74D", 
        orange_400: "#FFA726", 
        orange_500: "#FF9800", 
        orange_600: "#FB8C00", 
        orange_700: "#F57C00", 
        orange_800: "#EF6C00", 
        orange_900: "#E65100", 
        orange_A100: "#FFD180", 
        orange_A200: "#FFAB40", 
        orange_A400: "#FF9100", 
        orange_A700: "#FF6D00", 

        deep_orange_50: "#FBE9A7", 
        deep_orange_100: "#FFCCBC", 
        deep_orange_200: "#FFAB91", 
        deep_orange_300: "#FF8A65", 
        deep_orange_400: "#FF7043", 
        deep_orange_500: "#FF5722", 
        deep_orange_600: "#F4511E", 
        deep_orange_700: "#E64A19", 
        deep_orange_800: "#D84315", 
        deep_orange_900: "#BF360C", 
        deep_orange_A100: "#FF9E80", 
        deep_orange_A200: "#FF6E40", 
        deep_orange_A400: "#FF3D00", 
        deep_orange_A700: "#DD2600", 

        brown_50: "#EFEBE9", 
        brown_100: "#D7CCC8", 
        brown_200: "#BCAAA4", 
        brown_300: "#A1887F", 
        brown_400: "#8D6E63", 
        brown_500: "#795548", 
        brown_600: "#6D4C41", 
        brown_700: "#5D4037", 
        brown_800: "#4E342E", 
        brown_900: "#3E2723", 

        grey_50: "#FAFAFA", 
        grey_100: "#F5F5F5", 
        grey_200: "#EEEEEE", 
        grey_300: "#E0E0E0", 
        grey_400: "#BDBDBD", 
        grey_500: "#9E9E9E", 
        grey_600: "#757575", 
        grey_700: "#616161", 
        grey_800: "#424242", 
        grey_900: "#212121", 
        black_1000: "#000000", 
        white_1000: "#ffffff", 

        blue_grey_50: "#ECEFF1", 
        blue_grey_100: "#CFD8DC", 
        blue_grey_200: "#B0BBC5", 
        blue_grey_300: "#90A4AE", 
        blue_grey_400: "#78909C", 
        blue_grey_500: "#607D8B", 
        blue_grey_600: "#546E7A", 
        blue_grey_700: "#455A64", 
        blue_grey_800: "#37474F", 
        blue_grey_900: "#263238"
    };


    // Return a list of colors to be used
    __colors = Object.keys(__md)
        .map(function(k) { 
            return /[\w\d]+_200/.test(k)?
                    __md[k]: /[\w\d]+_300/.test(k)?
                    __md[k]: /[\w\d]+_400/.test(k)?
                    __md[k]: /[\w\d]+_500/.test(k)?
                    __md[k]: /[\w\d]+_600/.test(k)?
                    __md[k]: /[\w\d]+_700/.test(k)?
                    __md[k]: /[\w\d]+_800/.test(k)?
                    __md[k]: null 
        }).filter(function(c) { return c !== null; });
    
    // Transform a hxadecimal color to a rgba color
    __hexToRgb = function(hex, alpha) {
    	alpha = alpha || 1.0;
        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? (
        	"rgba(" +
            parseInt(result[1], 16) + ", " + 
            parseInt(result[2], 16) + ", " + 
            parseInt(result[3], 16) + ", " +
            alpha + ")"
        ) : null;
    }

    return {

    	// Return a list of colors
        getRandomColor : function(length) {
            length = length || 1;
            var selected = [];
            var isSelected = function(index) {
                return selected.filter(function(i) { return i === index; }).length > 0 ||
                length > __md.length;
            };

            for(var i = 0; i < length; i++) {
                var index = 0;

                do {
                    index = parseInt(Math.random() * __colors.length);
                } while(isSelected(index));

                selected.push(index);
            }

            return selected.map(function(i) { return __colors[i]; });
        }, 

        // Return a color by name
        color : function(colorName) {
            return __md[colorName];
        }, 
        
        // Return a color with especific alpha
        alpha : function(colorName, alpha) {
        	return __hexToRgb(__md[colorName], alpha);
        }

    };

} ();