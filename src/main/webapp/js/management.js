$(document).ready(function()
{

	var mgmtFunction = function(data)
	{   
		$('<tr>').html(
		"<th>GAME ID</th>" +
		"<th>REMAINING MOVES</th>" +
		"<th>USED LETTERS</th>" +
		"<th>PLAYING WORD</th>" +
		"<th>SECRET WORD</th>" +
		"<th>STATUS</th>").appendTo('#results');
		
		
            $(data).each(function(i, item) {
        	    $('<tr>').html("<td>" + 
        	    		item.id +  "</td><td>" +
        	    		item.remainingMoves + "</td><td>" +
        	            item.usedLetters + "</td><td>" +
        	            item.playingWord + "</td><td>" +
        	            item.secretWord + "</td><td>" +
        	            item.status + "</td>").appendTo('#results');
        	});
  
     };
	
 $.ajax({ 
          url: 'rest/game/management',
          dataType: 'json', 
          success: mgmtFunction         
      });
 
 $('#refresh').on('click', function() {
     var button = $(this).val();
     $('#results > tbody').html("");
     $.ajax({ // ajax call starts
         url: 'rest/game/management',
         dataType: 'json', // Choosing a JSON datatype
         success: mgmtFunction
     });
     return false; // keeps the page from not refreshing 
 });  
        
});