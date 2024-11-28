const path = require('path');

module.exports = {
  mode: 'development', // ou 'production' selon vos besoins
  entry: './src/index.js', // Point d'entrée de votre application
  output: {
    path: path.resolve(__dirname, 'dist'), // Dossier de sortie
    filename: 'bundle.js', // Nom du fichier de sortie
  },
  devServer: {
    contentBase: path.join(__dirname, 'dist'), // Dossier à servir
    compress: true,
    port: 3000, // Port du serveur de développement
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Remplacez par l'URL de votre serveur backend
        changeOrigin: true,
        pathRewrite: { '^/api': '' }
      },
    },
  },
  module: {
    rules: [
      {
        test: /\.js$/, // Pour les fichiers JavaScript
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader', // Utilisation de Babel pour transpiler le code
        },
      },
      {
        test: /\.css$/, // Pour les fichiers CSS
        use: ['style-loader', 'css-loader'], // Chargeur pour le CSS
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html', // Modèle HTML à utiliser
      filename: 'index.html', // Nom du fichier HTML généré
    }),
  ],
};