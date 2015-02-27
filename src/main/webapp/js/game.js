var paintRemainingMoves = function (remainingMoves)
{
		var text = "     ";
		for(var i = 0; i < remainingMoves; i++)
		{
			text = text + "* ";
		}
		text = text + " ";
		return text;
};


$(document).ready(function()
{
  //Start button
  $('#start').on('click', function() {
      var button = $(this).val();
      $('#introduction').hide();
      $('#win').hide();
      $('#gameover').hide();
      $('#loading').show();            
      $.ajax({ // ajax call starts
          url: 'rest/game/start',
          dataType: 'json', // Choosing a JSON datatype
          success: function(data) // Variable data contains the data we get from serverside
          {           	  
              $('#id').html(data.id);
              $('#rmoves').html("[ " + data.remainingMoves + " ]" + paintRemainingMoves(data.remainingMoves));
              $('#status').html(data.status);
              $('#usedLetters').html(data.usedLetters);
              $('#playingWord').html(data.playingWord);
              $('#gameboard').show();
              $('#loading').hide();
      
          }
      });
      return false; // keeps the page from not refreshing 
  });  
  
  //Play letter button
  $('#playLetter').on('click', function() 
		  {
      var button = $(this).val();
      if($('input:radio[name=letter]:checked').val() == null)
    	  {
    	   alert('Please, pick a letter');
    	   return false;
    	  }
      $.ajax({ // ajax call starts
    	  type:'GET',
          url: 'rest/game/letter',
          data: $('#formhangman').serialize(),
          dataType: 'json', // Choosing a JSON datatype
          success: function(data) // Variable data contains the data we get from serverside
          {           	          	  
        	  $('#id').html(data.id);
              $('#rmoves').html("[ " + data.remainingMoves + " ]" + paintRemainingMoves(data.remainingMoves));
              $('#status').html(data.status);
              $('#usedLetters').html(data.usedLetters);
              $('#playingWord').html(data.playingWord);
              if(data.status == "GAME_OVER")
            	  {
            	  	$('#gameboard').hide();
            	  	$('#gameover').show();
            	  	$('#introduction').show();
            	  }
              else if (data.status == 'WIN') 
              {
            	  $('#gameboard').hide();
          	  	  $('#win').show();
          	  	  $('#introduction').show();
			  }
          }
      });
      return false; // keeps the page from not refreshing 
  });
  
});