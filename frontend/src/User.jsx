import React from 'react'
import {TextField, Button} from '@material-ui/core'

const DEFAULT_DATE = "2000-01-01";

function formatDate(year, month, day) {
  if (month < 10)
    month = '0' + month;
  if (day < 10)
    day = '0' + day;

  return [year, month, day].join('-');
}

export default function User() {
  const [name, setName] = React.useState("");
  const [surname, setSurname] = React.useState("");
  const [birthDate, setBirthDate] = React.useState(DEFAULT_DATE);
  const [selectedFile, setSelectedFile] = React.useState(null);


  const saveUser = (user) => {
    return fetch("http://localhost:8080/api/users", {
      method: "POST", 
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(user)
    }).then(response =>  response.json())
  }

  const handleSubmit = () => {
    const toInput = { name, surname, dateOfBirth: birthDate.toISOString().substring(0, 10) };
    saveUser(toInput).then(data => {
      setName("");
      setSurname("");
      setBirthDate(DEFAULT_DATE);
    })
  }

  const handleScan = () => {
    fetch('https://api.microblink.com/v1/recognizers/blinkid', {
      method: "POST",
      headers: {
        "Authorization": "Bearer ZjRmM2MxZWY1NDljNGRmNWI4OTBmNzY0Njc3YWM3ODk6OWVmOTdlZmMtNzA0MS00ZTAyLTliMjgtOGFlZWIwNmQwMTgz",
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify({
        "imageSource": "https://narod.hr/wp-content/uploads/2015/02/OSOBNA.jpg",
        "returnFullDocumentImage": false,
        "returnFaceImage": false,
        "imageAnalysisResult": true
      })
    })
    .then(response =>  response.json())
    .then(data => {
        let userData = data.result
        setName(userData.firstName);
        setSurname(userData.lastName);
        let date = userData.dateOfBirth;
        setBirthDate(formatDate(date.year, date.month, date.day))
    })
  }

  return (
    <form>
      <TextField
        required
        fullWidth
        id="name"
        value={name}
        label="Name"
        name="name"
        autoComplete="name"
        variant="outlined"
        onChange={e => setName(e.target.value)}
      />
      <TextField
        autoComplete="surname"
        name="surname"
        variant="outlined"
        required
        fullWidth
        value={surname}
        id="surname"
        label="Surname"
        onChange={e => setSurname(e.target.value)}
      />
      <TextField
        id="birthDate"
        name="birthDate"
        label="Date of birth"
        type="date"
        value={birthDate}
        InputLabelProps={{shrink: true}}
        onChange={(date) => setBirthDate(date)}
      />

      <Button
        fullWidth
        variant="contained"
        preventDefault
        onClick={handleScan}
      >
        Scan
      </Button>
      
      <Button
        fullWidth
        variant="contained"
        color="primary"
        preventDefault
        onClick={handleSubmit}
      >
        Save
      </Button>
    </form>
  );
}
