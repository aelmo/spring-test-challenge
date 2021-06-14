# Endpoints

| US | METHOD | Endpoint | Body/Param | Validations |
| :---: | :---: | :---: | :---: | :---: |
| 01 | POST | {{localhost}}/propriedade/calculaMetrosQuadrados | [New Property Body](NewProperty.json) | Property Name is Valid/Not Empty // Property District is Valid/Not Empty // Room Name is Valid/Not Empty // Room Width is Valid/Not Empty // Room Length is Valid/Not Empty |
| 02 | POST | {{localhost}}/propriedade/calculaValorPropriedade | [New Property Body](NewProperty.json) | Property Name is Valid/Not Empty // Property District is Valid/Not Empty // Room Name is Valid/Not Empty // Room Width is Valid/Not Empty // Room Length is Valid/Not Empty |
| 03 | POST | {{localhost}}/propriedade/determinaMaiorSala | [New Property Body](NewProperty.json) | Property Name is Valid/Not Empty // Property District is Valid/Not Empty // Room Name is Valid/Not Empty // Room Width is Valid/Not Empty // Room Length is Valid/Not Empty |
| 04 | POST | {{localhost}}/propriedade/determinaMetrosQuadradosComodo | [New Property Body](NewProperty.json) | Property Name is Valid/Not Empty // Property District is Valid/Not Empty // Room Name is Valid/Not Empty // Room Width is Valid/Not Empty // Room Length is Valid/Not Empty |