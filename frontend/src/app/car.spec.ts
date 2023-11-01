import { Car } from './car';

describe('Car', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new Car()).toBeTruthy();
  });
});
