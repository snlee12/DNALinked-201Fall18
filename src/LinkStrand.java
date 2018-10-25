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
      copy = copy.next;
    }
    return myInfo.toString();
  }

  @Override
  public IDnaStrand reverse() {

    LinkStrand rev = new LinkStrand();
    rev.mySize = this.mySize;
    Node copyOriginal = this.myFirst;

    StringBuilder firstNodeInfo = new StringBuilder(copyOriginal.info);
    firstNodeInfo.reverse();
    rev.myFirst = new Node(firstNodeInfo.toString());
    Node copyRev = rev.myFirst;

    rev.myLast = copyRev;

    // Only goes here if there are more than 1 node
    if (copyOriginal.next != null) {

      // This first part makes a deep copy of the original IDnaStrand with the info reversed in each node
      while (copyOriginal.next != null) {
        StringBuilder revNodeInfo = new StringBuilder(copyOriginal.next.info);
        revNodeInfo.reverse();
        copyRev.next = new Node(revNodeInfo.toString());
        copyRev = copyRev.next;
        copyOriginal = copyOriginal.next;
      }

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
    }
    return rev;
  }

  @Override
  public int getAppendCount() {
    return myAppends;
  }

  @Override
  public char charAt(int index) throws IndexOutOfBoundsException {

    if (index >= mySize || index < 0) {
      throw new IndexOutOfBoundsException("index PARAMATER IS OUT OF BOUNDS!!!");
    }

    int count;

    // this resets if index is less than the one saved
    if(index < myIndex) {
      count = 0;
      myCurrent = myFirst;
      myLocalIndex = 0;
      }
    // this simply uses current stuff if index is greater than or equal to the one saved
    else {
      count = myIndex;
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
