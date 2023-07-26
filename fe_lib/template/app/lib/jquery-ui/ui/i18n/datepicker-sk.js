/* Slovak initialisation for the jQuery UI date picker plugin. */
/* Written by Vojtech Rinik (vojto@hmm.sk). */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/Datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.Datepicker );
	}
}( function( Datepicker ) {

Datepicker.regional.sk = {
	closeText: "Zavrieť",
	prevText: "&#x3C;Predchádzajúci",
	nextText: "Nasledujúci&#x3E;",
	currentText: "Dnes",
	monthNames: [ "január","február","marec","apríl","máj","jún",
	"júl","august","september","október","november","december" ],
	monthNamesShort: [ "Jan","Feb","Mar","Apr","Máj","Jún",
	"Júl","Aug","Sep","Okt","Nov","Dec" ],
	dayNames: [ "nedeľa","pondelok","utorok","streda","štvrtok","piatok","sobota" ],
	dayNamesShort: [ "Ned","Pon","Uto","Str","Štv","Pia","Sob" ],
	dayNamesMin: [ "Ne","Po","Ut","St","Št","Pia","So" ],
	weekHeader: "Ty",
	dateFormat: "dd.mm.yy",
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: "" };
Datepicker.setDefaults( Datepicker.regional.sk );

return Datepicker.regional.sk;

} ) );
