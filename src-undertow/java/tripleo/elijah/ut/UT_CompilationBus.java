package tripleo.elijah.ut;

import org.jetbrains.annotations.*;
import tripleo.elijah.comp.*;

import java.util.*;

public class UT_CompilationBus implements ICompilationBus {
	private final Compilation   c;
	private final UT_Controller utc;
	private final List<CB_Process> p = new ArrayList<>();
	List<CB_Action> actions = new ArrayList<>();
	private       CB_Process       last;


	public UT_CompilationBus(final Compilation aC, final UT_Controller aUTController) {
		c   = (aC);
		utc = aUTController;
		//utc.cb = this;
	}

	@Override
	public void option(final @NotNull CompilationChange aChange) {
		aChange.apply(c);
	}

	@Override
	public void inst(final @NotNull ILazyCompilerInstructions aLazyCompilerInstructions) {
		System.out.println("** [ci] " + aLazyCompilerInstructions.get().getFilename());
	}

	public void add(final CB_Action action) {
//		action.execute();
		actions.add(action);
	}

	@Override
	public void add(final CB_Process aProcess) {
		last = aProcess;
		p.add(last);
	}

	@Override
	public void add(final CB_Process aCBProcess, final Object aPayload) {
		if (aCBProcess instanceof CSS2_Advisable advisable) {
			advisable.adviseObject(aPayload);
		} else {
			throw new AssertionError();
		}
		add(aCBProcess);
	}

	public CB_Process getLast() {
		return last;
	}
}
