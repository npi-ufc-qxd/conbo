
var AutoComplete = function(setts) {
	this.__input = $(setts.input);
	this.__parent = this.__input.parent();
	this.__data = setts.data || {};
	this.__list = $($.parseHTML('<ul class="autocomplete-content dropdown-content"></ul>'));
	this.__onEdit = setts.onEdit || function() {};

	this.__match = function(text) {
		var self = this;
		return text.replace(" ", "") !== "" && this.__data.filter(function(d) {
			return new RegExp(text).test(d.text);
		});
	}

	this.__showDropDown = function(data) {
		var self = this;
		if(data.length > 0) {
			data.map(function(d) {
				var sItem = $($.parseHTML(
					'<li class="">' + 
					'	<span><span>' + d.text + '</span><a class="secondary-content"><i class="material-icons">edit</i></a> </span>' + 
					'</li>'
				));
				sItem.find("a").click(function() {
					self.__onEdit(d);
				})
				sItem.find("span > span").click(function() {
					self.__input.val(d.value);
					self.__hideDropDown();
				});
				sItem.find("span > span").css("width", "100%");
				self.__list.append(sItem);
			});
		}
	}

	this.__hideDropDown = function() {
		this.__list.find("li").remove();
	}

	this.init = function() {
		this.__parent.append(this.__list);

		var self = this;
		this.__input.keyup(function() {
			self.__hideDropDown();
			self.__showDropDown(self.__match($(this).val()));
		});
	}

	this.init();
}