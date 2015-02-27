package io.pivotal.rmqestimator.rest.mgmt.model;

import java.util.Arrays;

/**
 * Statistics about a Queue.
 * 
 * @author Richard Clayton (Berico Technologies)
 */
public class BackingQueueStatus {
	
	protected int q1;
	protected int q2;
	protected int q3;
	protected int q4;
	protected int len;
	protected Object[] delta;
	protected int pending_acks;
	protected String target_ram_count;
	protected int ram_msg_count;
	protected int ram_ack_count;
	protected int next_seq_id;
	protected int persistent_count;
	protected double avg_ingress_rate;
	protected double avg_egress_rate;
	protected double avg_ack_ingress_rate;
	protected double avg_ack_egress_rate;
	
	public int getQ1() {
		return q1;
	}
	
	public int getQ2() {
		return q2;
	}
	
	public int getQ3() {
		return q3;
	}
	
	public int getQ4() {
		return q4;
	}
	
	public int getLen() {
		return len;
	}
	
	public Object[] getDelta() {
		return delta;
	}
	
	public int getPendingAcks() {
		return pending_acks;
	}
	
	public String getTargetRamCount() {
		return target_ram_count;
	}
	
	public int getRamMsgCount() {
		return ram_msg_count;
	}
	
	public int getRamAckCount() {
		return ram_ack_count;
	}
	
	public int getNextSeqId() {
		return next_seq_id;
	}
	
	public int getPersistentCount() {
		return persistent_count;
	}
	
	public double getAvgIngressRate() {
		return avg_ingress_rate;
	}
	
	public double getAvgEgressRate() {
		return avg_egress_rate;
	}
	
	public double getAvgAckIngressRate() {
		return avg_ack_ingress_rate;
	}
	
	public double getAvgAckEgressRate() {
		return avg_ack_egress_rate;
	}
	
	@Override
	public String toString() {
		return "BackingQueueStatus [q1=" + q1 + ", q2=" + q2 + ", q3=" + q3
				+ ", q4=" + q4 + ", len=" + len + ", delta="
				+ Arrays.toString(delta) + ", pending_acks=" + pending_acks
				+ ", target_ram_count=" + target_ram_count + ", ram_msg_count="
				+ ram_msg_count + ", ram_ack_count=" + ram_ack_count
				+ ", next_seq_id=" + next_seq_id + ", persistent_count="
				+ persistent_count + ", avg_ingress_rate=" + avg_ingress_rate
				+ ", avg_egress_rate=" + avg_egress_rate
				+ ", avg_ack_ingress_rate=" + avg_ack_ingress_rate
				+ ", avg_ack_egress_rate=" + avg_ack_egress_rate + "]";
	}
}