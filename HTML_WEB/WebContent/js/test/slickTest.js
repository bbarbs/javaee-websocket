	
	var host = window.location.host;
    var path = window.location.pathname;
    var webProject = path.substring(0, path.indexOf('/', 1));

    var ws = new WebSocket("ws://" + host + webProject + "/equip/websocket");
    var rootURL = 'http://' + host + webProject + '/api/equip/';

    var myGrid = document.getElementById("myGrid");
    
    var grid;
    var dataView;  
    var data;	

	sendData();
	
     ws.onmessage = function(event) {
         data = event.data;
     }
     
     ws.onopen = function() {}

     function sendData() {
    	// Wait until the state of the socket is not ready and send the message when it is...
 	    waitForSocketConnection(ws, function(){
 	        ws.send("");
 	    });
    }

    // Make the function wait until the connection is made...
    function waitForSocketConnection(socket, callback){
        setTimeout(
            function () {
                if (socket.readyState === 1) {
                    if(callback != null){
                        callback();
                    }
                    return;
   	            } else {
   	                waitForSocketConnection(socket, callback);
   	            }

   	        }, 5); // wait 5 milisecond for the connection...
   	}

    var columns = [
                   {id: "pk", name: "PK", field: "pk"},
                   {id: "equipName", name: "Equipment", field: "equipName"},
                   {id: "license", name: "License", field: "license"},
                   {id: "state", name: "State", field: "state"},
                   {id: "efficiency", name: "Efficiency", field: "efficiency"},
                   {id: "activity", name: "Activity", field: "activity"},
                   {id: "operator", name: "Operator", field: "operator"}
                   ];	
    
    var options = {
    		enableCellNavigation: true,
    		enableColumnReorder: false
    		};

    dataView = new Slick.Data.DataView();	
    
    dataView.beginUpdate();
    dataView.setItems(data, 'pk');
    dataView.endUpdate();  
    
	grid = new Slick.Grid(myGrid, dataView, columns, options); 
    	
	// wire up model events to drive the grid
 	dataView.onRowCountChanged.subscribe(function (e, args) {
 	  grid.updateRowCount();
 	  grid.render();
 	});

 	dataView.onRowsChanged.subscribe(function (e, args) {
 	  grid.invalidateRows(args.rows);
 	  grid.render();
 	});
 	
 	 	

     	
        
        