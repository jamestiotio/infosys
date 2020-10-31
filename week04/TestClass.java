interface I1 {
  int p1();
}


interface I2 {
  int p2();
}


interface I3 {
  int p3();
}


interface I4 extends I1, I2, I3 {
  int p4();
}


interface I5 extends I3 {
  int p5();
}


abstract class C1 implements I4 {
  abstract int q1();
}


class C2 extends C1 implements I5 {

  @Override
  public int q1() {
    return 0;
  }

  @Override
  public int p1() {
    return 0;
  }

  @Override
  public int p2() {
    return 0;
  }

  @Override
  public int p3() {
    return 0;
  }

  @Override
  public int p4() {
    return 0;
  }

  @Override
  public int p5() {
    return 0;
  }

}


class C3 implements I5 {

  @Override
  public int p3() {
    return 0;
  }

  @Override
  public int p5() {
    return 0;
  }

}

