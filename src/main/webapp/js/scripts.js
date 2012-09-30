function sellItem(itemId) {
   $('#itemId').val(itemId);
   $('#sellInput').appendTo('#sell_'+itemId);
   $('#sellInput').removeClass('hide');
 }


