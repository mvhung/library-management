/* Norwegian Nynorsk initialisation for the jQuery UI date picker plugin. */
/* Written by Bjørn Johansen (post@bjornjohansen.no). */
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/Datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.Datepicker );
	}
}( function( Datepicker ) {

Datepicker.regional.nn = {
	closeText: "Lukk",
	prevText: "&#xAB;Førre",
	nextText: "Neste&#xBB;",
	currentText: "I dag",
	monthNames: [
		"januar",
		"februar",
		"mars",
		"april",
		"mai",
		"juni",
		"juli",
		"august",
		"september",
		"oktober",
		"november",
		"desember"
	],
	monthNamesShort: [ "jan","feb","mar","apr","mai","jun","jul","aug","sep","okt","nov","des" ],
	dayNamesShort: [ "sun","mån","tys","ons","tor","fre","lau" ],
	dayNames: [ "sundag","måndag","tysdag","onsdag","torsdag","fredag","laurdag" ],
	dayNamesMin: [ "su","må","ty","on","to","fr","la" ],
	weekHeader: "Veke",
	dateFormat: "dd.mm.yy",
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: ""
};
Datepicker.setDefaults( Datepicker.regional.nn );

return Datepicker.regional.nn;

} ) );
