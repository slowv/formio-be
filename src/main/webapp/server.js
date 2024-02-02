const express = require('express');
const path = require('path');

const app = express();
const port = process.env.PORT || 1998;

app.use('/assets', express.static(path.resolve(__dirname, 'src', 'assets')));
app.use('/app', express.static(path.resolve(__dirname, 'src', 'app'), {extensions: ['js']}));

// Only return index.html
app.get('/*', (req, res) => {
    res.sendFile(path.resolve(__dirname, 'src', 'index.html'));
});
app.listen(port, () => console.log(`Server running with port ${port}`));