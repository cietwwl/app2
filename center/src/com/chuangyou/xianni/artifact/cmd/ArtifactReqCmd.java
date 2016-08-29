package com.chuangyou.xianni.artifact.cmd;

import com.chuangyou.common.protobuf.pb.artifact.ArtifactReqProto.ArtifactReqMsg;
import com.chuangyou.xianni.artifact.ArtifactOperateAction;
import com.chuangyou.xianni.artifact.manager.ArtifactManager;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_ARTIFACT_REQ, desc = "神器操作请求")
public class ArtifactReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ArtifactReqMsg req = ArtifactReqMsg.parseFrom(packet.getBytes());
		switch(req.getAction()){
			case ArtifactOperateAction.REQUEST_ARTIFACTLIST:
				ArtifactManager.requestAllArtifact(player, req, packet.getCode());
				break;
			case ArtifactOperateAction.ARTIFACT_LEVELUP:
				ArtifactManager.artifactLevelup(player, req, packet.getCode());
				break;
			case ArtifactOperateAction.ARTIFACT_GRADEUP:
				ArtifactManager.artifactGradeup(player, req, packet.getCode());
				break;
			case ArtifactOperateAction.STONE_ACTIVATE:
				ArtifactManager.artifactStoneActivate(player, req, packet.getCode());
				break;
			case ArtifactOperateAction.STONE_LEVELUP:
				ArtifactManager.artifactStoneLevelup(player, req, packet.getCode());
				break;
		}
	}

}
