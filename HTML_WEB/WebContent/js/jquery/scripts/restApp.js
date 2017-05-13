/**
 * The root URL for the RESTful services
 */
var protocol = window.location.protocol;
var host = window.location.host;
var path = window.location.pathname;
var webProject = path.substring(0, path.indexOf('/', 1));

var rootURL = protocol + '//' + host + webProject + '/api/equip/';

/**
 * Retrieve equip list when application starts.
 */
findAll();

/**
 * Callback function when clicking btnAdd.
 */
$('#btnAdd').click(function() {
	// Execute.
	newEquip();
	// Prevent browser from scrolling.
	return false;
});

/**
 * Callback function when clicking btnSave.
 */
$('#btnSave').click(function() {
	if ($('#equipPk').val()=='') 
		// Add equipment if input equipPk is null.
		addEquip();
	else
		// Update if not.
		updateEquip();
	// Prevent browser from scrolling.
	return false;
});

/**
 * Callback when new equipment click.
 */
function newEquip() {
	// Empty inputs.
	clearDetails(); 
}

/**
 * Ajax request for getting list of equipment.
 */
function findAll() {	
	var req = $.ajax({
		type: 'GET',
		url: rootURL + 'list/',
		contentType: 'application/json; charset=utf-8', 
		dataType: 'json',
	});	
	req.done(function(data) {
		// Render details to Table.
		renderData(data);
		console.log(protocol);
	});	
}

/**
 * Ajax request for getting equipment data by id/pk.
 * @param id
 */
function findById(id) {
	var req = $.ajax({
		type: 'GET',
		url: rootURL + id,
		contentType: 'application/json; charset=utf-8', 
		dataType: 'json',
	});	
	req.done(function(data) {
		// Render details to DOM.
		renderEquipDetails(data);
	});
}

/**
 * Ajax request for adding new equipment.
 */
function addEquip() {
	var req = $.ajax({
		type: 'POST',
		url: rootURL,
		contentType: 'application/json; charset=utf-8', 
		dataType: 'json',
		data: formToJSON()
	});	
	req.always(function() {		
		// Retrieve equipment data.
		findAll();	
		// Clear inputs.
		clearDetails();
	});
}

function updateEquip() {
	var req = $.ajax({
		type: 'PUT',
		url: rootURL + $('#equipPk').val(),
		contentType: 'application/json; charset=utf-8', 
		dataType: 'json',
		data: formToJSON()
	});	
	req.always(function() {
		// Retrieve equipment data.
		findAll();
		// Clear inputs.
		clearDetails();
	});
}

function deleteEquip(equipPk) {
	var req = $.ajax({
		type: 'DELETE',
		url: rootURL + equipPk,
		contentType: 'application/json; charset=utf-8', 
		dataType: 'json',
	});	
	req.always(function() {
		// Retrieve equipment data.
		findAll();
	});
}

/**
 * Manage data.
 */
function renderData(data) {	
	// Check data validity and determine if it is array or not.
	var list = data == null ? [] : (data instanceof Array ? data : [data]);
	
	console.log("List: " + list);
	// Remove existing row in tbody of table.
	$('#equipTable tbody tr').remove();
	
	// Loop to json data and append it to tbody of table.
	$.each(list, function(index, equip) {
		
		console.log(index + " : " + equip.equipName);
		
		$('#equipTable tbody').append(
				'<tr>' 
					+ '<td>' + equip.equipName + '</td>'
					+ '<td>' + equip.license + '</td>'
					+ '<td>' + equip.state + '</td>'
					+ '<td>' + equip.efficiency + '</td>'
					+ '<td>' + equip.activity + '</td>'
					+ '<td>' + equip.operator + '</td>'
					+ '<td>' + '<button class="btn btn-primary btn-sm btnEdit" data-id=' + equip.pk + '>Edit</button>' + '</td>'
					+ '<td>' + '<button class="btn btn-danger btn-sm btnDelete" data-id=' + equip.pk + '>Delete</button>' + '</td>'
			  + '</tr>');
		
		
	});
	// Edit equipment.
	$('.btnEdit').bind('click', editEquip);
	// Delete equipment.
	$('.btnDelete').bind('click', removeEquip);
}

/**
 * Callback when btnEdit is click inside the table(#equipTable).
 * @returns {Boolean}
 */
function editEquip() {
	// Get the data-id and call ajax request.
	findById($(this).data('id'));
	// Prevent browser from scrolling.
	return false;
}

/**
 * Callback when btnDelete is click inside the table(#equipTable).
 * @returns {Boolean}
 */
function removeEquip() {
	// Make ajax request for deleting.
	deleteEquip($(this).data('id'));
	// Prevent browser from scrolling.
	return false;
}

/**
 * Assign data as inputs value.
 * @param data
 */
function renderEquipDetails(data) {		
	$.each(data, function(index, equip) {
		$('#equipPk').val(equip.pk);
		$('#name').val(equip.equipName);
		$('#license').val(equip.license);
		$('#state').val(equip.state);
		$('#efficiency').val(equip.efficiency);
		$('#activity').val(equip.activity);
		$('#operator').val(equip.operator);	
	});
}

/**
 * Empty inputs value.
 * @param equip
 */
function clearDetails(equip) {
	$('#equipPk').val('');
	$('#name').val('');
	$('#license').val('');
	$('#state').val('');
	$('#efficiency').val('');
	$('#activity').val('');
	$('#operator').val('');	
}

/**
 * Serialize inputs value to json.
 * @returns
 */
function formToJSON() {
	var equipPk = $('#equipPk').val();
	return JSON.stringify({
		'pk': equipPk == '' ? null : equipPk, 
		'equipName': $('#name').val(), 
		'license': $('#license').val(),
		'state': $('#state').val(),
		'efficiency': $('#efficiency').val(),
		'activity': $('#activity').val(),
		'operator': $('#operator').val()
		});
}
