import React, { Component } from 'react';
import { Row, Col, Button } from 'react-bootstrap';
import Dropzone from 'react-dropzone';
import request from 'superagent'

export default class StatementUpload extends Component {
  constructor(props) {
    super(props);
    this.onDrop = this.onDrop.bind(this);
    this.upload = this.upload.bind(this);
    this.cancel = this.cancel.bind(this);
    this.uploadCallback = this.uploadCallback.bind(this);
    this.state = { file: undefined, status: undefined, details: undefined }
  }

  render() {
    return (
      <Row>
        <Col md={12}>
          <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            minHeight: '100vh'
          }}>

            <div>
              {!this.state.file && <Row>
                <Col md={12}>
                  <Dropzone
                    onDrop={(files) => this.onDrop(files)}
                    style={{
                      height: '10vh',
                      boxShadow: 'rgba(0, 0, 0, 0.4) 0px 0px 4px',
                      padding: '10px',
                      backgroundColor: 'rgb(245, 245, 245)',
                      cursor:'pointer'
                    }}
                  >
                    <div style={{ textAlign: 'center' }}><strong>Drop the statement here</strong></div>
                    <div style={{ textAlign: 'center' }}><strong>Or click to select the statement to upload</strong></div>
                  </Dropzone>
                </Col>
              </Row>
              }

              {
                this.state.file && <Row>
                  <Col md={12}>
                    <div style={{
                      marginTop: '10px',
                      textAlign: 'center',
                      boxShadow: 'rgba(0, 0, 0, 0.4) 0px 0px 4px',
                      padding: '10px',
                      backgroundColor: '#DCEDC8'
                    }}>
                      {this.state.file.name}
                      <div style={{
                        marginTop: '10px'
                      }}>

                        {
                          this.state.status === 'uploading' && <div>
                            <i className='fa fa-spinner fa-pulse fa-3x fa-fw'></i>Uploading..
                            </div>
                        }

                        {
                          this.state.status === 'failed' && <div>
                            Upload failed <br />
                            <pre style={{ textAlign: 'left' }}>
                              {JSON.stringify(this.state.details.errror, null, 2)}
                            </pre>
                            <Button bsStyle='warning' bsSize='xSmall' onClick={this.cancel}>Close</Button>
                          </div>
                        }

                        {
                          this.state.status === 'success' && <div>
                            <div><strong>Uploaded!</strong></div>
                            <Button bsStyle='success' onClick={this.cancel}>Close</Button>
                          </div>
                        }

                        {
                          !this.state.status && <div>
                            <Button bsStyle='success' bsSize='large' onClick={this.upload}>Upload</Button>
                            <Button bsStyle='warning' bsSize='xSmall' onClick={this.cancel} style={{ marginLeft: '10px' }}>Cancel</Button>
                          </div>
                        }

                      </div>
                    </div>

                  </Col>
                </Row>
              }
            </div>

          </div>
        </Col>
      </Row>
    )
  }

  onDrop(acceptedFiles) {
    this.setState({ file: acceptedFiles[0], status: undefined, details: undefined });
  }

  upload() {
    const req = request.post('/api/portfolio/upload');
    req.attach("file", this.state.file);
    this.setState({ status: 'uploading', details: undefined })
    req.end(this.uploadCallback);
  }

  uploadCallback(err, res) {
    if (err) {
      this.setState({ status: 'failed', details: { errror: err } })
    } else {
      this.setState({ status: 'success', details: { response: res } });
    }
  }

  cancel() {
    this.setState({ file: undefined, status: undefined, details: undefined });
  }
}