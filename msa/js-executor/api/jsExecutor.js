'use strict';

const vm = require('vm');

function JsExecutor(useSandbox) {
    this.useSandbox = useSandbox;
}

JsExecutor.prototype.compileScript = function(code) {
    if (this.useSandbox) {
        return createScript(code);
    } else {
        return createFunction(code);
    }
}

JsExecutor.prototype.executeScript = function(script, args, timeout) {
    if (this.useSandbox) {
        return invokeScript(script, args, timeout);
    } else {
        return invokeFunction(script, args);
    }
}

function createScript(code) {
    return new Promise((resolve, reject) => {
        try {
            code = "("+code+")(...args)";
            var script = new vm.Script(code);
            resolve(script);
        } catch (err) {
            reject(err);
        }
    });
}

function invokeScript(script, args, timeout) {
    return new Promise((resolve, reject) => {
        try {
            var sandbox = Object.create(null);
            sandbox.args = args;
            var result = script.runInNewContext(sandbox, {timeout: timeout});
            resolve(result);
        } catch (err) {
            reject(err);
        }
    });
}


function createFunction(code) {
    return new Promise((resolve, reject) => {
        try {
            code = "return ("+code+")(...args)";
            const parsingContext = vm.createContext({});
            const func = vm.compileFunction(code, ['args'], {parsingContext: parsingContext});
            resolve(func);
        } catch (err) {
            reject(err);
        }
    });
}

function invokeFunction(func, args) {
    return new Promise((resolve, reject) => {
        try {
            var result = func(args);
            resolve(result);
        } catch (err) {
            reject(err);
        }
    });
}

module.exports = JsExecutor;
