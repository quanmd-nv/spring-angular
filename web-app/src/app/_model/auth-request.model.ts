export class AuthRequestModel {
  public constructor(
    public username: string,
    public password: string,
    public rememberMe: boolean
  ) {}
}
