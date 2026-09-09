package tripleo.elijah.ci;

import tripleo.vendor.antlr277.*;
import tripleo.elijah.lang.*;

public interface LibraryStatementPart {

	String getName();

	void setName(Token i1);

	String getDirName();

	void setDirName(Token dirName);

	void addDirective(Token token, IExpression iExpression);

	CompilerInstructions getInstructions();

	void setInstructions(CompilerInstructions instructions);

}