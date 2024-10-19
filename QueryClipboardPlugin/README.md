# QueryClipboardPlugin
## English
Project aiming to help Java developers when using NativeQuerys and StringBuilders with append method!

## Description
The QueryClipboardPlugin is an Eclipse plugin that captures SQL queries from the clipboard and inserts them into the active Java file in the format of a StringBuilder. The plugin detects the cursor's position in the code editor and adds the formatted query at the corresponding location.

## How to Use
**Keyboard Shortcut**: After installing the plugin, copy an SQL query to the clipboard.
In Eclipse, position the cursor in the Java file where you want to insert the StringBuilder.
Press **Ctrl + 6** (or your configured shortcut) to execute the plugin.
The code will be added at the cursor's position.

## Example
Suppose the SQL query on the clipboard is:
**SELECT * FROM users WHERE id = 1;**
After pressing **Ctrl + 6**, the plugin inserts:
**sql.append("SELECT * FROM users WHERE id = 1;");**
At the exact location where the cursor was in the Java file.
____________________________________________________________________________________________________________________________________________________________________
## Português
Projeto  visando auxiliar os desenvolvedores Java ao utilizar NativeQuerys e StringBuilders com metodo append!

## Descrição
O QueryClipboardPlugin é um plugin para o Eclipse que captura consultas SQL da área de transferência e as insere no arquivo Java ativo no formato de um `StringBuilder`. O plugin identifica a posição do cursor no editor de código e adiciona a query formatada no local correspondente.

## Funcionalidades
- Captura o conteúdo de texto da área de transferência.
- Insere consultas SQL como `sql.append("...")` no código Java.
- Adiciona o conteúdo na posição do cursor no arquivo.

## Como Usar
1. Atalho de Teclado: Após a instalação do plugin, selecione um texto SQL na área de transferência.
   - No Eclipse, posicione o cursor no arquivo Java onde deseja inserir o `StringBuilder`.
   - Pressione `Ctrl + 6` (ou o atalho configurado) para executar o plugin.
2. O código será adicionado no ponto do cursor.

## Exemplo
Suponha que a consulta SQL na área de transferência seja:
# SELECT * FROM users WHERE id = 1;

Após o atalho `Ctrl + 6`, o plugin insere:
# sql.append("SELECT * FROM users WHERE id = 1;");
No local exato onde o cursor estava no arquivo Java.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests com melhorias ou correções.

## Licença
Este projeto está licenciado sob a [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html).
