/**
 * 
 */

function chartExtender() {

	var o = this.cfg.data[0];


	var total = 0 ;
	
	
	$(o).map(function() {
		total += this[1];
	})
	
	myLabels = $.makeArray($(o).map(function() {
		return this[1] + "-" +
		Math.round(this[1] / total * 100) + "%";
	}));

	
	this.cfg.seriesDefaults.rendererOptions = {
		showDataLabels : true,
		dataLabels : myLabels,
		dataLabelPositionFactor : 1.2,
		
	};
}

function thursdayOnly(date) {
	var day = date.getDay();
	return [(day == 4), '']
	}

function mondayOnly(date) {
	var day = date.getDay();
	return [(day == 1), '']
	}