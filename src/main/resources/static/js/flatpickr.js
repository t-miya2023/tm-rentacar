let maxDate = new Date();
maxDate = maxDate.setMonth(maxDate.getMonth() + 3);

flatpickr('#fromStartDateToEndDate', {
	mode: "range",
	locale: 'ja',
	minDate: 'today',
	maxDate: maxDate,
	enableTime: true,
	dateFormat: "Y-m-d-H-i",
	altInput: true,
	altFormat: "m/d H:i",
	minuteIncrement: 30,
	onClose: function(selectedDates, dateStr, instance){
		const dates = dateStr.split(" から ");
		if(dates.length === 2){
			document.querySelector("input[name = 'startDate']").value = dates[0];
			document.querySelector("input[name = 'endDate']").value = dates[1];
		}else{
			document.querySelector("input[name = 'startDate']").value = "";
			document.querySelector("input[name = 'endDate']").value = "";
		}
	}
});