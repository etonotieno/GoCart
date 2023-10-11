---
name: Pull request
about: Create a pull request
label: 'triage me'
---
Thank you for opening a Pull Request!
Please make sure to read the [Contribution Guidelines](https://github.com/etonotieno/GoCart/blob/main/CONTRIBUTING.md)
and check that you understand and have followed it as best as possible
Before submitting your PR, please make sure you:

- [ ] Include a PR description which states **what you've done and why**
- [ ] Open a GitHub issue as a bug/feature request before writing your code! That way we can discuss the change, evaluate designs, and agree on the general idea
- [ ] Add/update necessary tests & ensure the tests pass
- [ ] Run `./codeAnalysis.sh` on linux/unix or `codeAnalysys.bat` on windows to make sure all lint/formatting checks have been done. Use `spotlessApply` to automatically apply formatting
- [ ] Update any relevant documentation

# Scope
Explain what your feature
does in a short paragraph. please check the below boxes
- [ ] I have followed the coding conventions
- [ ] I have added/updated necessary tests
- [ ] I have tested the changes added on a physical device

## Closes/Fixes Issues
Declare any issues by typing `Fixes #1` or `Closes #1` for example so that the automation can kick
in when this is merged

## Other testing QA Notes
What have you tested specifically and what possible impacts/areas there are that may need retesting
by others.

Please add a screenshot (if necessary)
