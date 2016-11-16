$(document).ready(function() {
	
	var refCard = $(".index__card-reference");
	refCard.remove();
	
	var container = $(".index__card-container");
	
	var items = $(".index__card");
	var savedMap = null;
	if(Cookies.get("mf-index-grid")) {
		savedMap = JSON.parse(Cookies.get("mf-index-grid"));
	}
	
	var grid = null;
	
	items.reverse();
	items.each(function(index, _) {
		
		var setts = $(this);
		var size = setts.data("size");
		var card = refCard.clone();
		card.removeClass("index__card-reference");
		card.find(".index__card-image-link").addClass(setts.data("color"));
		card.find(".index__card-title").text(setts.data("title"));
		card.find(".index__card-icon").addClass("mdi mdi-" + setts.data("icon"));
		card.find(".card__menu-drop-down").attr("id", card.find(".card__menu-drop-down").attr("id") + "-" + index);
		card.find(".card__menu").attr("data-activates", card.find(".card__menu").attr("data-activates") + "-" + index);
		
		card.find(".card__size-small").click(function() {
			grid.resize(card, 2, 2);
			grid.update(card, card.attr("data-gs-x"), card.attr("data-gs-y"), 2, 2);
			card.find(".index__card-action").hide();
			card.find(".index__card-image-link").addClass("full-height");
		});
		
		card.find(".card__size-medium").click(function() {
			grid.resize(card, 4, 4);
			if(card.attr("data-gs-x") > 8) {
				grid.update(card, 8, card.attr("data-gs-y"), 4, 4);
			} else {
				grid.update(card, card.attr("data-gs-x"), card.attr("data-gs-y"), 4, 4);
			}
			card.find(".index__card-action").show();
			card.find(".index__card-image-link").removeClass("full-height");
		});
		
		card.find(".card__size-large").click(function() {
			grid.resize(card, 8, 4);
			if(card.attr("data-gs-x") > 4) {
				grid.update(card, 4, card.attr("data-gs-y"), 8, 4);
			} else {
				grid.update(card, card.attr("data-gs-x"), card.attr("data-gs-y"), 8, 4);
			}
			card.find(".index__card-action").show();
			card.find(".index__card-image-link").removeClass("full-height");
		});
		
		try {
			if(savedMap) {
				
				card.attr("data-gs-x", savedMap[index].x);
				card.attr("data-gs-y", savedMap[index].y);
				card.attr("data-gs-width", savedMap[index].width);
				card.attr("data-gs-height", savedMap[index].height);
				
			} else {
				card.attr("data-gs-auto-position", true);
				if(size === "large") {
					card.attr("data-gs-width", 8);
					card.attr("data-gs-height", 4);
				} else if(size === "medium") {
					card.attr("data-gs-width", 4);
					card.attr("data-gs-height", 4);
				} if(size === "small") {
					card.attr("data-gs-width", 2);
					card.attr("data-gs-height", 2);
				}
				
			}
		} catch(err) { /* ... */ }
		
		card.find(".index__card-image-link").addClass(setts.data("color"));
		
		var refLink = card.find(".index__card-action-link-reference");
		refLink.remove();
		
		var linkContainer = card.find(".index__card-action");
		
		setts.find("a").each(function(index, _) {
			var link = refLink.clone();
			link.text($(this).text());
			link.attr("href", $(this).attr("href"));
			
			linkContainer.append(link);
			$(this).remove();
			
			if(index === 0)
				card.find(".index__card-image-link").attr("href", $(this).attr("href"));
		});
		
		if(card.attr("data-gs-width") == 2 && card.attr("data-gs-height") == 2) {
			linkContainer.hide();
			card.find(".index__card-image-link").addClass("full-height");
		}
		
		container.append(card);
		setts.remove();
		card.show();
	});
	
	var grid = $('.grid-stack').gridstack({
        cellHeight: 60, 
        animate: true, 
        disableDrag: false
    }).data('gridstack');
    
    var serializeGrid = function() {
    	var res = _.map($('.grid-stack .grid-stack-item:visible'), function (el) {
		    el = $(el);
		    var node = el.data('_gridstack_node');
		    return {
		        id: el.attr('data-custom-id'),
		        x: node.x,
		        y: node.y,
		        width: node.width,
		        height: node.height
		    };
		});
		Cookies.set("mf-index-grid", JSON.stringify(res));
		console.log("Saving: " + JSON.stringify(res));
	};
	
	$('.grid-stack').on('change', function(event, _) {
		serializeGrid();
	});
	
	$('.dropdown-button').dropdown({});
	
});