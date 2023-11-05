package chaaben_ramzi_tp3_;

import java.io.Serializable;
//activite 2.2
import java.net.ServerSocket;
public class Operation implements Serializable {
private int op1,op2,result;
private String operation;// + ou - ou / ou %
public Operation(int op1, int op2, String operation) {
this.op1=op1;
this.op2=op2;
this.operation=operation;
}
public int getOp1() {
return op1;
}
public void setOp1(int op1) {
this.op1 = op1;
}
public int getOp2() {
return op2;
}
public void setOp2(int op2) {
this.op2 = op2;
}
public int getResult() {
return result;
}
public void setResult(float nb) {
this.result = (int) nb;
}
public String getOperation() {
return operation;
}
public void setOperation(String operation) {
this.operation = operation;
}


}