config.module.rules.push({
  test: /\.m?js$/,
  exclude: /(node_modules|bower_components|packages_imported|akryl-dom.js)/,
  use: {
    loader: 'babel-loader',
    options: {
      presets: ['@babel/preset-env'],
      plugins: [
        ['babel-plugin-akryl', {}]
      ],
    }
  }
});
