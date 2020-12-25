'use strict';

const Long = require('long'),
      uuidParse = require('uuid-parse');

exports.toUUIDString = function(mostSigBits, leastSigBits) {
    var msbBytes = Long.fromValue(mostSigBits, false).toBytes(false);
    var lsbBytes = Long.fromValue(leastSigBits, false).toBytes(false);
    var uuidBytes = msbBytes.concat(lsbBytes);
    return uuidParse.unparse(uuidBytes);
}

exports.UUIDFromBuffer = function(buf) {
    return uuidParse.unparse(buf);
}

exports.UUIDToBits = function(uuidString) {
    const bytes = uuidParse.parse(uuidString);
    var msb = Long.fromBytes(bytes.slice(0,8), false, false).toString();
    var lsb = Long.fromBytes(bytes.slice(-8), false, false).toString();
    return [msb, lsb];
}
