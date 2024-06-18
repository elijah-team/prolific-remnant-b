package tripleo.elijah.ut;

import org.apache.commons.lang3.tuple.Pair;
import tripleo.elijah.comp.*;

import java.util.*;

public class UT_Controller extends _CompilerControllerBase implements CompilerController {
	private final UT_Root     utr;
	List<String>    args;
	String[]        args2;
	ICompilationBus cb;
	private       Compilation c;

	public UT_Controller(final UT_Root aUtr) {
		utr = aUtr;
	}

	@Override
	public void printUsage() {
		// redirect to usage page or put up dreaded dialog
		//System.out.println("Usage: eljc [--showtree] [-sE|O] <directory or .ez file names>");
	}

	@Override
	public void processOptions() {
		final OptionsProcessor             op  = new ApacheOptionsProcessor();
		final CompilerInstructionsObserver cio = new CompilerInstructionsObserver(c, c._cis);
		cb = new UT_CompilationBus(c, this);

		try {
			args2 = op.process(c, args, cb);
		} catch (final Exception e) {
			c.getErrSink().exception(e);
			throw new RuntimeException(e);
		}
	}

	public void _set(final Compilation aCompilation, final List<String> aArgumentList) {
		c    = aCompilation;
		args = aArgumentList;
	}

	public List<ICompilationBus.CB_Action> actions() {
		return ((UT_CompilationBus) cb).actions;
	}

	public UT_CompilationBus cb() {
		return (UT_CompilationBus) cb;
	}
}
