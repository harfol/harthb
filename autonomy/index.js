
if( msg.temperature >= 24 )
{

	return {msg: msg, metadata: metadata, msgType: msgType};
}else{

	return{
		"isSuccess": false
	}
}

