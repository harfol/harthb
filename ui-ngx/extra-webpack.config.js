const CompressionPlugin = require("compression-webpack-plugin");
const webpack = require("webpack");
const dirTree = require("directory-tree");

var langs = [];

dirTree("./src/assets/locale/", {extensions: /\.json$/}, (item) => {
  /* It is expected what the name of a locale file has the following format: */
  /* 'locale.constant-LANG_CODE[_REGION_CODE].json', e.g. locale.constant-es.json or locale.constant-zh_CN.json*/
  langs.push(item.name.slice(item.name.lastIndexOf("-") + 1, -5));
});

module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      TB_VERSION: JSON.stringify(require("./package.json").version),
      SUPPORTED_LANGS: JSON.stringify(langs),
    }),
    new CompressionPlugin({
      filename: "[path][base].gz[query]",
      algorithm: "gzip",
      test: /\.js$|\.css$|\.html$|\.svg?.+$|\.jpg$|\.ttf?.+$|\.woff?.+$|\.eot?.+$|\.json$/,
      threshold: 10240,
      minRatio: 0.8,
      deleteOriginalAssets: false,
    }),
  ],
};
