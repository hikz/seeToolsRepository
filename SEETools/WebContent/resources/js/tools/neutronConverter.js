$(function() {

	/*
	 * This function is used to update the environment section show status base
	 * on the select location by drop down value
	 */
	var updateEnvironmentShowStatus = function() {
		if ($("#neutronConverterForm\\:select-location-options").val() == "LOCATION_3D_COORDINATES") {
			$("#city-country-dropdown-container").hide();
		} else {
			$("#city-country-dropdown-container").show();
		}

		if ($("#neutronConverterForm\\:select-location-options").val() == "CITY_COUNTRY") {
			$("#latitude-longitude-altitude-text-container").hide();
		} else {
			$("#latitude-longitude-altitude-text-container").show();
		}

	};

	/*
	 * Select location drop down value change event
	 */
	$("#neutronConverterForm\\:select-location-options").change(function() {
		updateEnvironmentShowStatus();
	});

	/*
	 * Update data type field show status base on the data type drop down
	 * selection value
	 */
	var updateDataTypeShowStatus = function() {
		if ($("#neutronConverterForm\\:select-data-options").val() == "NEUTRON_UPSET_CROSS_SECTION"
				|| $("#neutronConverterForm\\:select-data-options").val() == "PROTON_SATURATED_UPSET_CROSS_SECTION") {
			$("#upset-cross-section-container").show();
			$("#heavy-ion-weibull-parameters-container").hide();
			$("#heavy-ion-cross-section-let-container").hide();
			$("#submitButton").show();
			$("#submitButton2").hide();
		}

		if ($("#neutronConverterForm\\:select-data-options").val() == "HEAVY_ION_WEIBULL_PARAMETERS") {
			$("#upset-cross-section-container").hide();
			$("#heavy-ion-weibull-parameters-container").show();
			$("#heavy-ion-cross-section-let-container").show();
			$("#submitButton2").show();
			$("#submitButton").hide();
		}

		if ($("#neutronConverterForm\\:select-data-options").val() == "HEAVY_ION_CROSS_SECTION_VS_LET") {
			$("#upset-cross-section-container").hide();
			$("#heavy-ion-weibull-parameters-container").hide();
			$("#heavy-ion-cross-section-let-container").show();
			$("#submitButton2").show();
			$("#submitButton").hide();
		}

		if ($("#neutronConverterForm\\:select-data-options").val() == "") {
			$("#upset-cross-section-container").hide();
			$("#heavy-ion-weibull-parameters-container").hide();
			$("#heavy-ion-cross-section-let-container").hide();
			$("#submitButton2").hide();
			$("#submitButton").show();
		}
	};

	/*
	 * Select data type drop down value change event
	 */
	$("#neutronConverterForm\\:select-data-options").change(function() {
		updateDataTypeShowStatus();
	});

	updateEnvironmentShowStatus();
	updateDataTypeShowStatus();

});
