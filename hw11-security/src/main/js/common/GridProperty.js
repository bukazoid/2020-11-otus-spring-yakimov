export default class GridProperty {
  constructor(id, label, multiline) {
    this.id = id;
    this.label = label;
    this.multiline = multiline == null ? false : multiline;
  }
}
