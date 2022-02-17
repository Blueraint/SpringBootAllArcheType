
$(function() {
	/*
	var dataSetList;
	var ajax = $.ajax({
        type: "GET",
        url: "/dataset/select",
        dataType:"json",
        async: false,
        success: function(data) {
        	dataSetList= data.list;
        }
    });
	*/
	
    $("#jsGrid").jsGrid({
        height: "auto",
        width: "100%",
 
        filtering: true,
        editing: true,
        sorting: true,
        paging: true,
        autoload: true,
 
        pageSize: 5,
        pageButtonCount: 5,
        pageIndex: 1,
 
        deleteConfirm: "Do you really want to delete the client?",
 
		  controller: {
		    loadData: function(filter) {
		      return $.ajax({
		        url: "/api/v1/dept",
		        dataType: "json"
		      });
		    }
		  },
//      data : dataSetList,
        
        fields: [
            { name: "deptNo", type: "number", width: 50 },
            { name: "deptName", type: "text", width: 150 },
            { name: "location", type: "number", width: 250 }
        ]
    });
    
});