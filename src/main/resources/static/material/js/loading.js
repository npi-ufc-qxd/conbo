jQuery.fn.extend({
	loading : function(settings) {
		return new Loading(this, settings);
	}
});

var Loading = function(elementWrapper, settings) {

	/*
		PRIVATE
	*/

	this.__defaultSettings = {
		type: 'circular', 
		size: 'medium', 
		minValue: 0, 
		maxValue: 100, 
		value: 0, 
		indeterminate: true, 
	};

	this.__linearLoaderTemplate = '' + 
		'<div class="progress no-margin">' + 
	    	'<div class="" style="width: 70%"></div>' + 
	    '</div>';

    this.__circularLoaderTemplate = '' + 
		'<div class="preloader-wrapper active">' + 
			'<div class="spinner-layer spinner-cyan-only">' + 
				'<div class="circle-clipper left">' + 
					'<div class="circle"></div>' + 
				'</div><div class="gap-patch">' + 
					'<div class="circle"></div>' + 
				'</div><div class="circle-clipper right">' + 
					'<div class="circle"></div>' + 
				'</div>' + 
			'</div>' + 
		'</div>';

	this.__init = function() {
		this.settings = $.extend(this.__defaultSettings, settings);
		this.elementWrapper = elementWrapper;

		this.__render();
	};

	this.__changeValue = function() {
		var progress = this.settings.value * 100 / (this.settings.maxValue - this.settings.minValue);
			this.loader.children().attr("style", "width: " + parseInt(progress) + "%");
	}

	this.__render = function() {
		this.loader = $($.parseHTML(
			this.settings.type === 'circular'? this.__circularLoaderTemplate: this.__linearLoaderTemplate
		));

		if(this.settings.type === 'circular') {
			this.loader.addClass(this.settings.size);
		} else {
			this.loader.children().addClass(this.settings.indeterminate? 'indeterminate': 'determinate');

			this.__changeValue();
		}

		this.elementWrapper.append(this.loader);
		this.loader.hide();
	}

	/*
		PUBLIC
	*/	

	this.setValue = function(value) {
		this.settings.value = 
			value < this.settings.minValue? this.settings.minValue:
			value > this.settings.maxValue? this.settings.maxValue:
			value;
	};

	this.show = function() {
		this.loader.show();
	}

	this.hide = function() {
		this.loader.hide();
	}

	this.finish = function() {
		this.loader.remove();
	}

	this.__init();

	return this;

}





