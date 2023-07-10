const hljs = require("highlight/lib/core");
hljs.registerLanguage("xml", require("highlight/lib/languages/xml"));
hljs.registerLanguage("json", require("highlight/lib/languages/json"));

module.exports = hljs;
