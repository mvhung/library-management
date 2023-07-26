/* Canadian-French initialisation for the jQuery UI date picker plugin. */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/Datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.Datepicker );
	}
}( function( Datepicker ) {

Datepicker.regional[ "fr-CA" ] = {
	closeText: "Fermer",
	prevText: "Précédent",
	nextText: "Suivant",
	currentText: "Aujourd'hui",
	monthNames: [ "janvier", "février", "mars", "avril", "mai", "juin",
		"juillet", "août", "septembre", "octobre", "novembre", "décembre" ],
	monthNamesShort: [ "janv.", "févr.", "mars", "avril", "mai", "juin",
		"juil.", "août", "sept.", "oct.", "nov.", "déc." ],
	dayNames: [ "dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi" ],
	dayNamesShort: [ "dim.", "lun.", "mar.", "mer.", "jeu.", "ven.", "sam." ],
	dayNamesMin: [ "D", "L", "M", "M", "J", "V", "S" ],
	weekHeader: "Sem.",
	dateFormat: "yy-mm-dd",
	firstDay: 0,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: ""
};
Datepicker.setDefaults( Datepicker.regional[ "fr-CA" ] );

return Datepicker.regional[ "fr-CA" ];

} ) );
