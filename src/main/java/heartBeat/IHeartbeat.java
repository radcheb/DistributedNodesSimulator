package heartBeat;

import java.util.List;

import com.radcheb.nodesSimulator.INode;

public interface IHeartbeat {

	List<INode> getMembersList();

	void declareFailed(INode node);
	
	void isFailed(INode node);
}
