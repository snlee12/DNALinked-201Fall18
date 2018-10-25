public class LinkStrand implements IDnaStrand {

  private class Node {
    String info;
    Node next;
    public Node(String s) {
      info = s;
      next = null;
    }
  }

  private Node myFirst,myLast;
  private long mySize;
  private int myAppends;
  private int myIndex;
  private int myLocalIndex;
  private Node myCurrent;

  public LinkStrand() {
    this("");
  }

  public LinkStrand(String s) {
    initialize(s);
  }

  @Override
  public void initialize(String source) {
    myFirst = new Node(source);
    myLast = myFirst;
    mySize = source.length();
    myAppends = 0;
    myIndex = 0;
    myLocalIndex = 0;
    myCurrent = myFirst;
  }

  @Override
  public long size() {
    return mySize;
  }

  @Override
  public IDnaStrand getInstance(String source) {
    return new LinkStrand(source);
  }

  @Override
  public IDnaStrand append(String dna) {
    myLast.next = new Node(dna);
    myLast = myLast.next;
    mySize += dna.length();
    myAppends += 1;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder myInfo = new StringBuilder();
    Node copy = myFirst;
    while (copy != null) {
      myInfo.append(copy.info);
      myFirst = copy.next;
    }
    return myInfo.toString();
  }

  @Override
  public IDnaStrand reverse() {
    // This first part makes a deep copy of the original IDnaStrand
    LinkStrand rev = new LinkStrand();
    Node copyOriginal = this.myFirst;
    rev.myFirst = new Node(this.myFirst.info);
    Node copyRev = rev.myFirst;
    while (copyOriginal.next != null) {
      StringBuilder revNodeInfo = new StringBuilder(copyOriginal.next.info);
      revNodeInfo.reverse();
      copyRev.next = new Node(revNodeInfo.toString());
      copyRev = copyRev.next;
      copyOriginal = copyOriginal.next;
    }
    rev.myLast = copyRev;

    // We reverse the copy of the original IDnaStrand, giving the reverse
    Node prev = null;
    Node current = rev.myFirst;
    Node next = null;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    rev.myLast = rev.myFirst;
    rev.myFirst = prev;

    return rev;
  }

  @Override
  public int getAppendCount() {
    return myAppends;
  }

  @Override
  public char charAt(int index) {

    if(index < myIndex) {
      int count = 0;
      }
    else {
      int count = myIndex;
      }
    while (count != index) {
      count++;
      myLocalIndex++;
      if (myLocalIndex >= myCurrent.info.length()) {
        myLocalIndex = 0;
        myCurrent = myCurrent.next;
        }
      }

    myIndex = index;
    return myCurrent.info.charAt(myLocalIndex);
  }

}
