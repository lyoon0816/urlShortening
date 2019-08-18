function _isNullValueCheck(uValue){
	
	if ( typeof(uValue) !== "undefined" && uValue !== null && uValue != '')
		return true;
	else
		return false;
}

function _Ajax(url,type,data,dataType,sync,runFunc){
	var temVal='';
	var returnVal='';
	
	$.ajax({
			url : url
		,	type : type
		,	dataType: dataType
		,	data: data
		,	async :sync
		,	success:function(result)
			{
				returnVal = true;
				eval(runFunc+'(result)');
			}
		,	error:function(request,status,error)
			{
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				returnVal = false;
			}
	}); 
	return returnVal;
};
