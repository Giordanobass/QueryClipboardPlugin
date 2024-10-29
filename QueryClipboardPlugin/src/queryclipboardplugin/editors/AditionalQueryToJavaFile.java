package queryclipboardplugin.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.texteditor.ITextEditor;

public class AditionalQueryToJavaFile {

	FormatCodeInEditor format = new FormatCodeInEditor();

	void addQueryToJavaFile(ITextEditor textEditor, String[] queryLines) {

		try {
			// Obter o documento do editor
			IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());

			// Obter a posição do cursor no documento
			ITextSelection selection = (ITextSelection) textEditor.getSelectionProvider().getSelection();
			int cursorPosition = selection.getOffset();

			// Cria o conteúdo a ser adicionado
			StringBuilder sb = new StringBuilder();
			for (String line : queryLines) {
				sb.append("sql.append(\" ").append(line.trim()).append("\");\n");
			}

			// Insere o conteúdo da área de transferência na posição do cursor
			document.replace(cursorPosition, 0, sb.toString());

			// Aplicar formatação de código
			format.formatCodeInEditor(textEditor, cursorPosition, sb.length());
			
			 // Restaurar a posição do cursor
            textEditor.selectAndReveal(cursorPosition, 0);

			System.out.println("Conteúdo adicionado na posição do cursor.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
