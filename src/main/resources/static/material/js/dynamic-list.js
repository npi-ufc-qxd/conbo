 var DynamicList = function(setts) {

	this.__id = null;
	this.__list = $(setts.list);
	this.__element = $(setts.element);
	this.__elementContainer = $(setts.elementContainer);
	this.__addButton = $(setts.addButton);
	this.__removeButtonSelector = setts.removeButton;
	this.__onAdd = setts.onAdd || function() { return {}; };
	this.__onAdded = setts.onAdded || function() {};
	this.__onRemove = setts.onRemove || function() { return true; };
	this.__onRemoved = setts.onRemoved || function() {};
	this.__insertAtBegin = setts.insertAtBegin || false;
	
	/*
	 * Helpers
	 */

	this.__actionPrefixes = {
		ADDING: "adding", 
		ADDED: "added", 
		EDITING: "editing", 
		EDITED: "edited", 
		REMOVING: "removing", 
		REMOVED: "removed"
	};

	this.__dataPrefixes = {
		CREATED_ELEMENT: "generated-element", 
		ID: "id"
	};

	this.__prefix = function() {
		var args = Array.prototype.slice.call(arguments);
		return "dynamic-list-" + args.reduce(function(p1, p2) {
			return p1 + "-" + p2;
		});
	}

	this.__now = function(func) {
		setTimeout(func, 0);
	}

	this.__after = function(func, offset) {
		offset = offset || 100;
		setTimeout(func, offset);
	}

	this.__iterateClass = function(element) {
		var classes = Array.prototype.slice.call(arguments);
		var prevClazz = null;
		var offset = 0;
		var self = this;
		classes.map(function(clazz) {
			if(!prevClazz) {
				prevClazz = clazz;
				return;
			}

			self.__after(function() {
				element.removeClass(prevClazz);
				element.addClass(clazz);
				prevClazz = clazz;
			}, offset);
			offset += 100;
		});
	}

	this.__getElementCount = function() {
		return $("." + this.__prefix(this.__dataPrefixes.CREATED_ELEMENT)).length;
	}

	this.__getFirstElement = function() {
		return $("." + this.__prefix(this.__dataPrefixes.CREATED_ELEMENT)).first();
	}

	this.__updateToolTip = function() {
		/*
		this.__after(function() {
			$('.tooltipped').tooltip('remove');
			$('.tooltipped').tooltip({delay: 1000});
		});
		*/
	}

	/*
	 * Main functions
	 */

	this.init = function() {
		this.__id = DynamicList.serial();
		this.__element.remove();

		var self = this;
		this.__addButton.click(function() {
			self.__onAdd(function(data) {
				self.addItem(data);
			});
		});
	};

	this.addItem = function(data) {
		
		if(!data) return; 

		// Setup element
		var element = this.__element.clone();	

		for(k in data) {
			var el = element.find(k);
			for(attr in data[k]) {
				if(attr === "text") el.text(data[k][attr]);
				else if (attr === "value") el.val(data[k][attr]);
				else element.attr(attr, data[k][attr]);
			}
		}

		var id = DynamicList.serial();
		element.data(this.__prefix(this.__dataPrefixes.ID), id);
		element.addClass(this.__prefix(this.__dataPrefixes.CREATED_ELEMENT, id));
		var removeBtn = element.find(this.__removeButtonSelector);
		var self = this;
		removeBtn.click(function() {
			self.__onRemove(element, function() {
				self.removeItem(element);
			});				
		});

		// Adding element
		if(this.__insertAtBegin && this.__getElementCount() > 0) {
			element.insertBefore(this.__getFirstElement());
		} else {
			this.__elementContainer.append(element);
		}
		this.__iterateClass(element, 
			this.__prefix(this.__actionPrefixes.ADDING), 
			this.__prefix(this.__actionPrefixes.ADDED)
		);

		this.__onAdded(element);
		
		return element;

	};

	this.removeItem = function(element) {
		var self = this;
		this.__iterateClass(element, 
			self.__prefix(self.__actionPrefixes.REMOVING), 
			self.__prefix(self.__actionPrefixes.REMOVED)
		);
		this.__after(function() {
			element.remove();
			self.__onRemoved(element);
		}, 500);
	}

	this.init();

};

DynamicList.SERIAL = 0;
DynamicList.serial = function() {
	return DynamicList.SERIAL++;
}